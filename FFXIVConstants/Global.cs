using System.Collections.Generic;

namespace FFXIVConstants
{
    public static class Global
    {
        public const string Version = "6.35";

        public static Dictionary<string, object> Constants = new Dictionary<string, object>
        {
            { "InventoryOperationBaseValue", 0x00EB },
            { "RECV", "49 8B 40 10 4C 8B 50 38" }
        };
    }
}
