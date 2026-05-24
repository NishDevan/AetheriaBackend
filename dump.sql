-- Drop existing tables if they exist
DROP TABLE IF EXISTS boss_progress CASCADE;
DROP TABLE IF EXISTS enemy_spawns CASCADE;
DROP TABLE IF EXISTS world_blocks CASCADE;
DROP TABLE IF EXISTS inventory_slots CASCADE;
DROP TABLE IF EXISTS inventories CASCADE;
DROP TABLE IF EXISTS save_states CASCADE;
DROP TABLE IF EXISTS worlds CASCADE;
DROP TABLE IF EXISTS items CASCADE;
DROP TABLE IF EXISTS players CASCADE;

-- Create Players Table
CREATE TABLE players (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create Worlds Table
CREATE TABLE worlds (
    id BIGSERIAL PRIMARY KEY,
    player_id BIGINT NOT NULL,
    world_name VARCHAR(100) NOT NULL,
    seed BIGINT NOT NULL,
    biome_type VARCHAR(20) NOT NULL,
    difficulty VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_played_at TIMESTAMP,
    FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
);

-- Create Items Table
CREATE TABLE items (
    id BIGSERIAL PRIMARY KEY,
    item_code VARCHAR(50) NOT NULL UNIQUE,
    item_name VARCHAR(100) NOT NULL,
    item_type VARCHAR(20) NOT NULL,
    stack_limit INT NOT NULL DEFAULT 99,
    icon_path VARCHAR(255)
);

-- Create Save States Table
CREATE TABLE save_states (
     id BIGSERIAL PRIMARY KEY,
     world_id BIGINT NOT NULL UNIQUE,
     player_x DECIMAL(10, 2) NOT NULL,
     player_y DECIMAL(10, 2) NOT NULL,
     player_velocity_x DECIMAL(10, 2) NOT NULL,
     player_velocity_y DECIMAL(10, 2) NOT NULL,
     player_grounded BOOLEAN NOT NULL DEFAULT FALSE,
     player_health INT NOT NULL DEFAULT 100,
     selected_hotbar_index INT NOT NULL DEFAULT 0,
     player_facing_right BOOLEAN NOT NULL DEFAULT TRUE,
     king_slime_defeated BOOLEAN NOT NULL DEFAULT FALSE,
     saved_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (world_id) REFERENCES worlds(id) ON DELETE CASCADE
);

-- Create Inventories Table
CREATE TABLE inventories (
     id BIGSERIAL PRIMARY KEY,
     world_id BIGINT NOT NULL UNIQUE,
     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     FOREIGN KEY (world_id) REFERENCES worlds(id) ON DELETE CASCADE
);

-- Create Inventory Slots Table
CREATE TABLE inventory_slots (
     id BIGSERIAL PRIMARY KEY,
     inventory_id BIGINT NOT NULL,
     slot_index INT NOT NULL,
     item_id BIGINT,
     amount INT NOT NULL DEFAULT 0,
     CONSTRAINT unique_slot UNIQUE (inventory_id, slot_index),
     FOREIGN KEY (inventory_id) REFERENCES inventories(id) ON DELETE CASCADE,
     FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE SET NULL
);

-- Create World Blocks Table
CREATE TABLE world_blocks (
    id BIGSERIAL PRIMARY KEY,
    world_id BIGINT NOT NULL,
    block_x INT NOT NULL,
    block_y INT NOT NULL,
    block_type VARCHAR(50) NOT NULL,
    is_solid BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT unique_block UNIQUE (world_id, block_x, block_y),
    FOREIGN KEY (world_id) REFERENCES worlds(id) ON DELETE CASCADE
);

-- Create Enemy Spawns Table
CREATE TABLE enemy_spawns (
    id BIGSERIAL PRIMARY KEY,
    world_id BIGINT NOT NULL,
    enemy_type VARCHAR(50) NOT NULL,
    x DECIMAL(10, 2) NOT NULL,
    y DECIMAL(10, 2) NOT NULL,
    health INT NOT NULL,
    is_boss BOOLEAN NOT NULL DEFAULT FALSE,
    is_defeated BOOLEAN NOT NULL DEFAULT FALSE,
    dropped_item_id BIGINT,
    FOREIGN KEY (world_id) REFERENCES worlds(id) ON DELETE CASCADE,
    FOREIGN KEY (dropped_item_id) REFERENCES items(id) ON DELETE SET NULL
);

-- Create Boss Progress Table
CREATE TABLE boss_progress (
    id BIGSERIAL PRIMARY KEY,
    world_id BIGINT NOT NULL UNIQUE,
    boss_name VARCHAR(50) NOT NULL,
    spawned BOOLEAN NOT NULL DEFAULT FALSE,
    defeated BOOLEAN NOT NULL DEFAULT FALSE,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (world_id) REFERENCES worlds(id) ON DELETE CASCADE
);                                                                                                                                                         (2,
