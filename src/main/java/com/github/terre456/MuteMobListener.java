package com.github.terre456;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.papermc.paper.event.player.PlayerNameEntityEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;

public class MuteMobListener implements Listener {
  public static final String muteName = "Censored";

  @EventHandler
  public void onNameChanged(PlayerNameEntityEvent event) {
    LivingEntity mob = event.getEntity();
    Player player = event.getPlayer();
    TextComponent newName = (TextComponent) event.getName();
    
    if (! mob.getName().equals(muteName) && newName.content().equals(muteName)) {
      sendDebug(player, "Mob is now muted (rename it to unmute it)");
      mob.setSilent(true);
    } else if (mob.getName().equals(muteName) && ! newName.content().equals(muteName)){
      mob.setSilent(false); 
    }
  }

  public static void sendDebug(Player player, String message) {
    Component debugMessage = Component.text("[Info] ", NamedTextColor.DARK_AQUA)
        .append(Component.text(message, NamedTextColor.GRAY));
    player.sendMessage(debugMessage);
  }
}
