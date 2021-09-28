// Generated by https://github.com/zhyupe/ffxiv-opcode-worker

namespace FFXIVOpcodes.CN
{
    enum ServerLobbyIpcType : ushort
    {

    };

    enum ClientLobbyIpcType : ushort
    {

    };

    enum ServerZoneIpcType : ushort
    {        
        // Server Zone
        ActorCast = 0x00EF,
        ActorControl = 0x01D8,
        ActorControlSelf = 0x0354,
        Effect = 0x006E,
        EffectResult = 0x0315,
        EventFinish = 0x0283,
        EventPlay = 0x030D,
        EventPlay4 = 0x02D2,
        EventStart = 0x013A,
        InitZone = 0x0343,
        PlayerSetup = 0x01E2,
        PlayerStats = 0x03A0,
        PrepareZoning = 0x01BD,
        SomeDirectorUnk4 = 0x03C5,
        StatusEffectList = 0x0347,
        UpdateClassInfo = 0x02AA,
        WeatherChange = 0x0118,
    };

    enum ClientZoneIpcType : ushort
    {
        ClientTrigger = 0x0270,
    };

    enum ServerChatIpcType : ushort
    {

    };

    enum ClientChatIpcType : ushort
    {

    };
}
