package com.devan.aetheria.backend.config;

import com.devan.aetheria.backend.entities.BossEntity;
import com.devan.aetheria.backend.entities.BossProgressEntity;
import com.devan.aetheria.backend.entities.EnemyEntity;
import com.devan.aetheria.backend.entities.InventoryEntity;
import com.devan.aetheria.backend.entities.ItemEntity;
import com.devan.aetheria.backend.entities.PlayerEntity;
import com.devan.aetheria.backend.repositories.BossProgressRepository;
import com.devan.aetheria.backend.repositories.BossRepository;
import com.devan.aetheria.backend.repositories.EnemyRepository;
import com.devan.aetheria.backend.repositories.InventoryRepository;
import com.devan.aetheria.backend.repositories.ItemRepository;
import com.devan.aetheria.backend.repositories.PlayerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final PlayerRepository playerRepository;
    private final ItemRepository itemRepository;
    private final InventoryRepository inventoryRepository;
    private final EnemyRepository enemyRepository;
    private final BossRepository bossRepository;
    private final BossProgressRepository bossProgressRepository;

    public DataSeeder(PlayerRepository playerRepository,
                      ItemRepository itemRepository,
                      InventoryRepository inventoryRepository,
                      EnemyRepository enemyRepository,
                      BossRepository bossRepository,
                      BossProgressRepository bossProgressRepository) {
        this.playerRepository = playerRepository;
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
        this.enemyRepository = enemyRepository;
        this.bossRepository = bossRepository;
        this.bossProgressRepository = bossProgressRepository;
    }

    @Override
    public void run(String... args) {
        if (playerRepository.count() > 0 || itemRepository.count() > 0) {
            return;
        }

        ItemEntity dirt = new ItemEntity();
        dirt.setItemName("Dirt");
        dirt.setItemType(0);
        dirt.setPower(0f);

        ItemEntity stone = new ItemEntity();
        stone.setItemName("Stone");
        stone.setItemType(0);
        stone.setPower(0f);

        ItemEntity treeLog = new ItemEntity();
        treeLog.setItemName("Tree Log");
        treeLog.setItemType(0);
        treeLog.setPower(0f);

        ItemEntity treeTrunk = new ItemEntity();
        treeTrunk.setItemName("Tree Trunk");
        treeTrunk.setItemType(0);
        treeTrunk.setPower(0f);

        ItemEntity leaves = new ItemEntity();
        leaves.setItemName("Leaves");
        leaves.setItemType(0);
        leaves.setPower(0f);

        ItemEntity coalOre = new ItemEntity();
        coalOre.setItemName("Coal Ore");
        coalOre.setItemType(0);
        coalOre.setPower(0f);

        ItemEntity ironOre = new ItemEntity();
        ironOre.setItemName("Iron Ore");
        ironOre.setItemType(0);
        ironOre.setPower(0f);

        ItemEntity pickaxe = new ItemEntity();
        pickaxe.setItemName("Pickaxe");
        pickaxe.setItemType(1);
        pickaxe.setPower(2.5f);

        ItemEntity axe = new ItemEntity();
        axe.setItemName("Axe");
        axe.setItemType(1);
        axe.setPower(2.0f);

        ItemEntity sword = new ItemEntity();
        sword.setItemName("Sword");
        sword.setItemType(2);
        sword.setPower(3.5f);

        ItemEntity slimeBall = new ItemEntity();
        slimeBall.setItemName("Slime Ball");
        slimeBall.setItemType(3);
        slimeBall.setPower(0f);

        ItemEntity healthPotion = new ItemEntity();
        healthPotion.setItemName("Health Potion");
        healthPotion.setItemType(3);
        healthPotion.setPower(25f);

        ItemEntity gelCore = new ItemEntity();
        gelCore.setItemName("Gel Core");
        gelCore.setItemType(3);
        gelCore.setPower(0f);

        itemRepository.saveAll(List.of(
                dirt, stone, treeLog, treeTrunk, leaves, coalOre, ironOre,
                pickaxe, axe, sword, slimeBall, healthPotion, gelCore
        ));

        PlayerEntity player = new PlayerEntity();
        player.setUsername("danis");
        player.setHealth(100);
        player.setLocX(5f);
        player.setLocY(2f);
        player.setLevel(1);
        player = playerRepository.save(player);

        PlayerEntity guest = new PlayerEntity();
        guest.setUsername("guest");
        guest.setHealth(90);
        guest.setLocX(2f);
        guest.setLocY(1f);
        guest.setLevel(1);
        guest = playerRepository.save(guest);

        InventoryEntity slot0 = new InventoryEntity();
        slot0.setPlayer(player);
        slot0.setItem(pickaxe);
        slot0.setQuantity(1);
        slot0.setSlotIndex(0);

        InventoryEntity slot1 = new InventoryEntity();
        slot1.setPlayer(player);
        slot1.setItem(sword);
        slot1.setQuantity(1);
        slot1.setSlotIndex(1);

        InventoryEntity slot2 = new InventoryEntity();
        slot2.setPlayer(player);
        slot2.setItem(dirt);
        slot2.setQuantity(20);
        slot2.setSlotIndex(2);

        InventoryEntity slot3 = new InventoryEntity();
        slot3.setPlayer(player);
        slot3.setItem(treeLog);
        slot3.setQuantity(8);
        slot3.setSlotIndex(3);

        InventoryEntity slot4 = new InventoryEntity();
        slot4.setPlayer(player);
        slot4.setItem(coalOre);
        slot4.setQuantity(6);
        slot4.setSlotIndex(4);

        InventoryEntity guestSlot0 = new InventoryEntity();
        guestSlot0.setPlayer(guest);
        guestSlot0.setItem(axe);
        guestSlot0.setQuantity(1);
        guestSlot0.setSlotIndex(0);

        InventoryEntity guestSlot1 = new InventoryEntity();
        guestSlot1.setPlayer(guest);
        guestSlot1.setItem(healthPotion);
        guestSlot1.setQuantity(2);
        guestSlot1.setSlotIndex(1);

        inventoryRepository.saveAll(List.of(slot0, slot1, slot2, slot3, slot4, guestSlot0, guestSlot1));

        EnemyEntity slime = new EnemyEntity();
        slime.setName("Slime");
        slime.setMaxHealth(6);
        slime.setBaseDamage(1);
        slime.setDropItem(slimeBall);
        enemyRepository.save(slime);

        EnemyEntity forestSlime = new EnemyEntity();
        forestSlime.setName("Forest Slime");
        forestSlime.setMaxHealth(8);
        forestSlime.setBaseDamage(2);
        forestSlime.setDropItem(slimeBall);
        enemyRepository.save(forestSlime);

        EnemyEntity caveSlime = new EnemyEntity();
        caveSlime.setName("Cave Slime");
        caveSlime.setMaxHealth(10);
        caveSlime.setBaseDamage(2);
        caveSlime.setDropItem(slimeBall);
        enemyRepository.save(caveSlime);

        BossEntity kingSlime = new BossEntity();
        kingSlime.setName("King Slime");
        kingSlime.setMaxHealth(40);
        kingSlime.setBaseDamage(3);
        kingSlime.setSpecialDrop(slimeBall);
        kingSlime = bossRepository.save(kingSlime);

        BossEntity queenSlime = new BossEntity();
        queenSlime.setName("Queen Slime");
        queenSlime.setMaxHealth(55);
        queenSlime.setBaseDamage(4);
        queenSlime.setSpecialDrop(gelCore);
        queenSlime = bossRepository.save(queenSlime);

        BossProgressEntity bossProgress = new BossProgressEntity();
        bossProgress.setPlayer(player);
        bossProgress.setBoss(kingSlime);
        bossProgress.setIsDefeated(false);
        bossProgressRepository.save(bossProgress);

        BossProgressEntity bossProgress2 = new BossProgressEntity();
        bossProgress2.setPlayer(player);
        bossProgress2.setBoss(queenSlime);
        bossProgress2.setIsDefeated(false);
        bossProgressRepository.save(bossProgress2);
    }
}
