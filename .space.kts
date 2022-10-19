job("Upload opcode files to COS and Refresh CDN") {
    startOn {
        gitPush {
            branchFilter {
                +"refs/heads/master"
            }
            pathFilter {
                +"opcodes.min.json"
            }
        }
    }
    
    container(displayName = "Upload to COS Bucket", image = "python:3-alpine") {
        env["COS_BUCKET_NAME"] = Params("opcodes_cos_bucket_name")
        env["COS_BUCKET_REGION"] = Params("cos_bucket_region")
        env["COS_SECRET_ID"] = Secrets("cos_secret_id")
        env["COS_SECRET_KEY"] = Secrets("cos_secret_key")
        
        shellScript {
            interpreter = "/bin/bash"
            // note that you should escape the $ symbol in a Kotlin way
            content = """
            	echo "Uploading files to COS..."
                apk --no-cache add g++ make
                pip install coscmd
                coscmd config -a ${'$'}COS_SECRET_ID -s ${'$'}COS_SECRET_KEY -b ${'$'}COS_BUCKET_NAME -r ${'$'}COS_BUCKET_REGION
        		coscmd upload opcodes.min.json /opcodes/opcodes.min.json
        		coscmd upload constants.min.json /constants/constants.min.json
            """
        }
    }
    
    container(displayName = "Upload to COS Bucket", image = "python:3-alpine") {
        env["CDN_HOST"] = Params("opcodes_cdn_host")
        
        shellScript {
            interpreter = "/bin/bash"
            // note that you should escape the $ symbol in a Kotlin way
            content = """
  				echo "upgrade tccli"
            	pip uninstall tccli jmespath
            	pip install tccli
            	
                echo "Purge opcode cache"
            	tccli configure set secretId ${'$'}COS_SECRET_ID secretKey ${'$'}COS_SECRET_KEY
            	tccli cdn PurgeUrlsCache --cli-unfold-argument --Urls ${'$'}CDN_HOST/opcodes/opcodes.min.json
            	tccli cdn PurgeUrlsCache --cli-unfold-argument --Urls ${'$'}CDN_HOST/constants/constants.min.json
            """
        }
    }
}
