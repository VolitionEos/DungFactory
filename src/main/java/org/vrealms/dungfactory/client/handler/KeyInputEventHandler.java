package org.vrealms.dungfactory.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import org.vrealms.dungfactory.client.settings.Keybindings;
import org.vrealms.dungfactory.reference.Key;

public class KeyInputEventHandler
{
    private static Key getPressedKeybinding()
    {
        if (Keybindings.dropdung.isPressed())
        {
            return Key.DUNGDROP;
        }
        //else if (Keybindings.OTHERKEY.isPressed()) { return Key.OTHERKEY;}

        return  Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {

    }
}
