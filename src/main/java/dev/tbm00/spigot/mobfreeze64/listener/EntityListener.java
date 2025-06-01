package dev.tbm00.spigot.mobfreeze64.listener;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.md_5.bungee.api.ChatColor;

public class EntityListener implements Listener {

    String[] boss = new String[]{"ENDER_DRAGON", "WITHER", "WARDEN", "PLAYER"};


    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Material material = player.getInventory().getItemInMainHand().getType();
        Entity entity = event.getRightClicked();
        
        if (player.hasPermission("mobfreeze64.freeze") && event.getHand().name().equals("HAND") && !Arrays.asList(this.boss).contains(entity.getType().toString())) {
            if (material == Material.ICE || material == Material.PACKED_ICE || material == Material.BLUE_ICE) {
                Mob mob = (Mob)event.getRightClicked();
                if (mob.hasAI()) {
                    mob.setAI(false);
                    player.sendMessage(ChatColor.GREEN + "Successfully froze " + mob.getName()+"!");
                    return;
                } else {
                    mob.setAI(true);
                    player.sendMessage(ChatColor.GREEN + "Successfully unfroze " + mob.getName()+"!");
                    return;
                }
            }
        }

        if (player.hasPermission("mobfreeze64.godmode") && event.getHand().name().equals("HAND") && !Arrays.asList(this.boss).contains(entity.getType().toString())) {
            if (material == Material.BEDROCK) {
                Mob mob = (Mob)event.getRightClicked();
                if (mob.isInvulnerable()) {
                    mob.setInvulnerable(false);
                    player.sendMessage(ChatColor.GREEN + "Successfully turned godmode off for " + mob.getName()+"!");
                    return;
                } else {
                    mob.setInvulnerable(true);
                    player.sendMessage(ChatColor.GREEN + "Successfully turned godmode on for " + mob.getName()+"!");
                    return;
                }
            }
        }
    }
}