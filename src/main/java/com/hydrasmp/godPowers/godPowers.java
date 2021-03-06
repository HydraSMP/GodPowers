package com.hydrasmp.godPowers;


import com.hydrasmp.godPowers.commands.*;
import com.hydrasmp.godPowers.listeners.EntityListener;
import com.hydrasmp.godPowers.listeners.PlayerListener;

import com.hydrasmp.godPowers.util.StringHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;


public class godPowers extends JavaPlugin {
    @SuppressWarnings("unused")
    private Logger log;
    public String title = "";
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();
    public HashMap<String, Integer> curse = new HashMap<String, Integer>();
    public ArrayList<String> godmodeEnabled = new ArrayList<String>();
    public ArrayList<String> isJesus = new ArrayList<String>();
    public ArrayList<MedusaPlayer> isUnderMedusaInfluence = new ArrayList<MedusaPlayer>();
    public ArrayList<String> isInferno = new ArrayList<String>();
    public ArrayList<String> isHermes = new ArrayList<String>();
    public ArrayList<String> isPoseidon = new ArrayList<String>();
    public ArrayList<String> isMedusa = new ArrayList<String>();
    public ArrayList<String> superJumper = new ArrayList<String>();
    public ArrayList<String> arrowKill = new ArrayList<String>();
    public ArrayList<String> burn = new ArrayList<String>();
    public ArrayList<String> gaia = new ArrayList<String>();
    public ArrayList<String> isZeus = new ArrayList<String>();
    public ArrayList<String> isVulcan = new ArrayList<String>();
    public ArrayList<String> DemiGod = new ArrayList<String>();
    public ArrayList<String> hades = new ArrayList<String>();
    public ArrayList<Material> shovelDrops = new ArrayList<Material>();
    public ArrayList<Material> pickDrops = new ArrayList<Material>();
    public ArrayList<Material> axeDrops = new ArrayList<Material>();
    public HashMap<String, String> list = new HashMap<String, String>();
    public double DemiModifier = 0;
    public boolean godModeOnLogin = true;
    public boolean godTools = true;
    public int medusaFreezeTime = 10;
    public File file;

    public void loadConfig() {
        try {
            this.saveDefaultConfig();
            if (!this.getConfig().contains("GodModeTitle"))
                this.getConfig().set("GodModeTitle", "[God] ");
            if (!this.getConfig().contains("GodModeOnLogin"))
                this.getConfig().set("GodModeOnLogin", true);
            if (!this.getConfig().contains("DemiGodDamageModifier"))
                this.getConfig().set("DemiGodDamageModifier", 0.2);
            if (!this.getConfig().contains("GodToolsEnabled"))
                this.getConfig().set("GodToolsEnabled", true);
            if (!this.getConfig().contains("MedusaFreezeTime"))
                this.getConfig().set("MedusaFreezeTime", 10);
            title = this.getConfig().getString("GodModeTitle", "");
            godModeOnLogin = this.getConfig().getBoolean("GodModeOnLogin", true);
            DemiModifier = this.getConfig().getDouble("DemiGodDamageModifier", 0.2);
            godTools = this.getConfig().getBoolean("GodToolsEnabled", true);
            medusaFreezeTime = this.getConfig().getInt("MedusaFreezeTime", 10);
            this.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[godPowers] Error loading config file.");

        }
    }

    public void onDisable() {
    }

    @Override
    public void onEnable() {
        file = this.getFile();
        loadConfig();
        StringHandler.init(this);
        @SuppressWarnings("unused")
        BukkitTask TaskName = new OnOneSecond(this).runTaskTimer(this, 25, 25);
        String error = StringHandler.COMMAND_REGISTER_ERROR;
        try {
            getCommand("zeus").setExecutor(new ZeusCommand(this));
            list.put("zeus", "- " + StringHandler.LIST_ZEUS_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "zeus.");
        }
        try {
            getCommand("godmode").setExecutor(new GodModeCommand(this));
            list.put("godmode", "[Player] - " + StringHandler.LIST_GODMODE_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "godmode.");
        }
        try {
            getCommand("godmodeon").setExecutor(new GodModeCommand(this));
        } catch (Exception e) {
            System.out.println(error + "godmodeon.");
        }
        try {
            getCommand("godmodeoff").setExecutor(new GodModeCommand(this));
        } catch (Exception e) {
            System.out.println(error + "godmodeoff.");
        }
        try {
            getCommand("jesus").setExecutor(new JesusCommand(this));
            list.put("jesus", "- " + StringHandler.LIST_JESUS_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "jesus.");
        }
        try {
            getCommand("die").setExecutor(new DieCommand(this));
            list.put("die", "- " + StringHandler.LIST_DIE_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "die.");
        }
        try {
            getCommand("slay").setExecutor(new SlayCommand(this));
            list.put("slay", "[Player] <arrows/fire/drop> - " + StringHandler.LIST_SLAY_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "slay.");
        }
        try {
            getCommand("smite").setExecutor(new SlayCommand(this));
            list.put("smite", "[Player] <arrows/fire/drop> - " + StringHandler.LIST_SLAY_DESCRIPTION);
        } catch (Exception e1) {
            System.out.println(error + "smite.");
        }
        try {
            getCommand("maim").setExecutor(new MaimCommand(this));
            list.put("maim", "[Player] - " + StringHandler.LIST_MAIM_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "maim.");
        }
        try {
            getCommand("inferno").setExecutor(new InfernoCommand(this));
            list.put("inferno", "- " + StringHandler.LIST_INFERNO_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "inferno.");
        }
        try {
            getCommand("superjump").setExecutor(new SuperJumpCommand(this));
            list.put("superjump", "- " + StringHandler.LIST_SUPERJUMP_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "superjump.");
        }
        try {
            getCommand("gaia").setExecutor(new GaiaCommand(this));
            list.put("gaia", "- " + StringHandler.LIST_GAIA_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "gaia.");
        }
        try {
            getCommand("heal").setExecutor(new HealCommand(this));
            list.put("heal", "<Player> - " + StringHandler.LIST_HEAL_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "heal.");
        }
        try {
            getCommand("godpowers").setExecutor(new GodPowersCommand(this));
            list.put("godpowers", "- Displays this message.");
        } catch (Exception e) {
            System.out.println(error + "godpowers. How dare they!");
        }
        try {
            getCommand("vulcan").setExecutor(new VulcanCommand(this));
            list.put("vulcan", "- " + StringHandler.LIST_VULCAN_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "vulcan.");
        }
        try {
            getCommand("myballsareonfire").setExecutor(new VulcanCommand(this));
        } catch (Exception e) {
            System.out.println(error + "myballsareonfire.");
        }
        try {
            getCommand("demigod").setExecutor(new DemiGodCommand(this));
            list.put("demigod", "- " + StringHandler.LIST_DEMIGOD_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "demigod.");
        }
        try {
            getCommand("hades").setExecutor(new HadesCommand(this));
            list.put("hades", "- " + StringHandler.LIST_HADES_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "hades.");
        }
        try {
            getCommand("bless").setExecutor(new BlessCommand(this));
            list.put("bless", "[Player] - " + StringHandler.LIST_BLESS_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "bless.");
        }
        try {
            getCommand("fusrodah").setExecutor(new FusRoDAHCommand(this));
            list.put("FusRoDAH", "- " + StringHandler.LIST_FUSRODAH_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "FusRoDAHCommand.");
        }
        try {
            getCommand("plutus").setExecutor(new PlutusCommand(this));
            list.put("plutus", "- " + StringHandler.LIST_PLUTUS_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "plutus");
        }
        try {
            getCommand("dupe").setExecutor(new DupeCommand(this));
            list.put("dupe", "<amount> - " + StringHandler.LIST_DUPE_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "dupe.");
        }
        try {
            getCommand("medusa").setExecutor(new MedusaCommand(this));
            list.put("medusa", "- " + StringHandler.LIST_MEDUSA_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "medusa.");
        }
        try {
            getCommand("hermes").setExecutor(new HermesCommand(this));
            list.put("hermes", "- " + StringHandler.LIST_HERMES_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "hermes. ");
        }
        try {
            getCommand("poseidon").setExecutor(new PoseidonCommand(this));
            list.put("poseidon", "- " + StringHandler.LIST_POSEIDON_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "poseidon.");
        }
        try {
            getCommand("repair").setExecutor(new RepairCommand(this));
            list.put("repair", "- " + StringHandler.LIST_REPAIR_DESCRIPTION);
        } catch (Exception e) {
            System.out.println(error + "repair.");
        }
        try {
            getCommand("itemrepair").setExecutor(new RepairCommand(this));
        } catch (Exception e) {
            System.out.println(error + "itemrepair.");
        }
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EntityListener(this), this);
        pm.registerEvents(new PlayerListener(this), this);
        PluginDescriptionFile pdfFile = this.getDescription();
        System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
        populateLists();
    }

    public void populateLists() {
        shovelDrops.add(Material.GRASS);
        shovelDrops.add(Material.DIRT);
        shovelDrops.add(Material.SAND);
        shovelDrops.add(Material.GRAVEL);
        shovelDrops.add(Material.CLAY);
        pickDrops.add(Material.STONE);
        pickDrops.add(Material.COBBLESTONE);
        pickDrops.add(Material.GOLD_ORE);
        pickDrops.add(Material.IRON_ORE);
        pickDrops.add(Material.COAL_ORE);
        pickDrops.add(Material.LAPIS_ORE);
        pickDrops.add(Material.LAPIS_BLOCK);
        pickDrops.add(Material.SANDSTONE);
        pickDrops.add(Material.GOLD_BLOCK);
        pickDrops.add(Material.IRON_BLOCK);
        pickDrops.add(Material.DOUBLE_STEP);
        pickDrops.add(Material.STEP);
        pickDrops.add(Material.BRICK);
        pickDrops.add(Material.MOSSY_COBBLESTONE);
        pickDrops.add(Material.OBSIDIAN);
        pickDrops.add(Material.DIAMOND_ORE);
        pickDrops.add(Material.DIAMOND_BLOCK);
        axeDrops.add(Material.WOOD);
        axeDrops.add(Material.LOG);

    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }

    public void dropDeadItems(Player player) {
        if (player.getInventory() != null) {
            ItemStack[] item = player.getInventory().getContents();
            Location position = new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
            for (ItemStack anItem : item) {
                if (anItem != null && anItem.getType() != Material.AIR) {
                    player.getWorld().dropItemNaturally(position, anItem);
                }
            }
        }
    }

    public void arrowSlay(Location arrows, World world, Player player) {
        arrows = new Location(world, player.getLocation().getX() + 2, player.getLocation().getY() + 1, player.getLocation().getZ() + 2);
        Arrow arrow = world.spawnArrow(arrows, new Vector(5, 1, 5), 2.0f, 4.0f);
        arrow.setFireTicks(100);
        arrow.teleport((Entity) player);
    }

    public void bless(Player p) {
        for (ItemStack i : p.getInventory().getContents()) {
            if (i != null) {
                switch (i.getType()) {
                    // Iron Shovel
                    case IRON_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Iron Pickaxe
                    case IRON_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Iron Axe
                    case IRON_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Bow
                    case BOW:
                        i.addEnchantment(Enchantment.ARROW_DAMAGE, 5);
                        i.addEnchantment(Enchantment.ARROW_FIRE, 1);
                        i.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                        break;
                    // Iron Sword
                    case IRON_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    // Wooden Sword
                    case WOOD_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    // Wooden Shovel
                    case WOOD_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Wooden Pickaxe
                    case WOOD_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Wooden Axe
                    case WOOD_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Stone Sword
                    case STONE_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    // Stone Shovel
                    case STONE_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Stone Pickaxe
                    case STONE_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Stone Axe
                    case STONE_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Diamond Sword
                    case DIAMOND_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    // Diamond Shovel
                    case DIAMOND_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Diamond Pickaxe
                    case DIAMOND_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Diamond Axe
                    case DIAMOND_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Gold Sword
                    case GOLD_SWORD:
                        i.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                        i.addEnchantment(Enchantment.DAMAGE_ARTHROPODS, 5);
                        i.addEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
                        i.addEnchantment(Enchantment.FIRE_ASPECT, 2);
                        i.addEnchantment(Enchantment.KNOCKBACK, 2);
                        i.addEnchantment(Enchantment.LOOT_BONUS_MOBS, 3);
                        break;
                    // Gold Shovel
                    case GOLD_SPADE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Gold Pickaxe
                    case GOLD_PICKAXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Gold Axe
                    case GOLD_AXE:
                        i.addEnchantment(Enchantment.DIG_SPEED, 5);
                        i.addEnchantment(Enchantment.DURABILITY, 3);
                        i.addEnchantment(Enchantment.SILK_TOUCH, 1);
                        i.addEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
                        break;
                    // Leather Helmet
                    case LEATHER_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Leather Chestplate
                    case LEATHER_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Leather Leggings
                    case LEATHER_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Leather Boots
                    case LEATHER_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Chainmail Helmet
                    case CHAINMAIL_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Chainmail Chestplate
                    case CHAINMAIL_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Chainmail Leggings
                    case CHAINMAIL_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Chainmail Boots
                    case CHAINMAIL_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Iron Helmet
                    case IRON_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Iron Chestplate
                    case IRON_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Iron Leggings
                    case IRON_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Iron Boots
                    case IRON_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Diamond Helmet
                    case DIAMOND_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Diamond Chestplate
                    case DIAMOND_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Diamond Leggings
                    case DIAMOND_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Diamond Boots
                    case DIAMOND_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Gold Helmet
                    case GOLD_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Gold Chestplate
                    case GOLD_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Gold Leggings
                    case GOLD_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Gold Boots
                    case GOLD_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                }
            }
        }
        for (ItemStack i : p.getInventory().getArmorContents()) {
            if (i != null) {
                switch (i.getType()) {
                    // Leather Helmet
                    case LEATHER_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Leather Chestplate
                    case LEATHER_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Leather Leggings
                    case LEATHER_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Leather Boots
                    case LEATHER_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Chainmail Helmet
                    case CHAINMAIL_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Chainmail Chestplate
                    case CHAINMAIL_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Chainmail Leggings
                    case CHAINMAIL_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Chainmail Boots
                    case CHAINMAIL_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Iron Helmet
                    case IRON_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Iron Chestplate
                    case IRON_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Iron Leggings
                    case IRON_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Iron Boots
                    case IRON_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Diamond Helmet
                    case DIAMOND_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Diamond Chestplate
                    case DIAMOND_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Diamond Leggings
                    case DIAMOND_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Diamond Boots
                    case DIAMOND_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Gold Helmet
                    case GOLD_HELMET:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.OXYGEN, 3);
                        i.addEnchantment(Enchantment.WATER_WORKER, 1);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Gold Chestplate
                    case GOLD_CHESTPLATE:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Gold Leggings
                    case GOLD_LEGGINGS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                    // Gold Boots
                    case GOLD_BOOTS:
                        i.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
                        i.addEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FIRE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
                        i.addEnchantment(Enchantment.PROTECTION_FALL, 4);
                        i.addEnchantment(Enchantment.THORNS, 3);
                        break;
                }
            }
        }
    }
}




