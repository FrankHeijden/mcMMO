package com.gmail.nossr50.util.blockmeta.chunkmeta;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class NullChunkManager implements ChunkManager {

    @Override
    public void closeAll() {}

    @Override
    public ChunkStore readChunkStore(World world, int x, int z) throws IOException {
        return null;
    }

    @Override
    public CompletableFuture<ChunkStore> readChunkStoreAsync(World world, int x, int z) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void writeChunkStore(World world, int x, int z, ChunkStore data) {}

    @Override
    public CompletableFuture<Void> writeChunkStoreAsync(World world, int x, int z, ChunkStore data) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void closeChunkStore(World world, int x, int z) {}

    @Override
    public CompletableFuture<Void> closeChunkStoreAsync(World world, int x, int z) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void loadChunklet(int cx, int cy, int cz, World world) {}

    @Override
    public CompletableFuture<Void> loadChunkletAsync(int cx, int cy, int cz, World world) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void unloadChunklet(int cx, int cy, int cz, World world) {}

    @Override
    public CompletableFuture<Void> unloadChunkletAsync(int cx, int cy, int cz, World world) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void loadChunk(int cx, int cz, World world, Entity[] entities) {}

    @Override
    public CompletableFuture<Void> loadChunkAsync(int cx, int cz, World world, Entity[] entities) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void unloadChunk(int cx, int cz, World world) {}

    @Override
    public CompletableFuture<Void> unloadChunkAsync(int cx, int cz, World world) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void saveChunk(int cx, int cz, World world) {}

    @Override
    public CompletableFuture<Void> saveChunkAsync(int cx, int cz, World world) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public boolean isChunkLoaded(int cx, int cz, World world) {
        return true;
    }

    @Override
    public void chunkLoaded(int cx, int cz, World world) {}

    @Override
    public CompletableFuture<Void> chunkLoadedAsync(int cx, int cz, World world) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void chunkUnloaded(int cx, int cz, World world) {}

    @Override
    public CompletableFuture<Void> chunkUnloadedAsync(int cx, int cz, World world) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void saveWorld(World world) {}

    @Override
    public void unloadWorld(World world) {}

    @Override
    public void loadWorld(World world) {}

    @Override
    public void saveAll() {}

    @Override
    public void unloadAll() {}

    @Override
    public boolean isTrue(int x, int y, int z, World world) {
        return false;
    }

    @Override
    public CompletableFuture<Boolean> isTrueAsync(int x, int y, int z, World world) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public boolean isTrue(Block block) {
        return false;
    }

    @Override
    public CompletableFuture<Boolean> isTrueAsync(Block block) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public boolean isTrue(BlockState blockState) {
        return false;
    }

    @Override
    public CompletableFuture<Boolean> isTrueAsync(BlockState blockState) {
        return CompletableFuture.completedFuture(false);
    }

    @Override
    public void setTrue(int x, int y, int z, World world) {}

    @Override
    public CompletableFuture<Void> setTrueAsync(int x, int y, int z, World world) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void setTrue(Block block) {}

    @Override
    public CompletableFuture<Void> setTrueAsync(Block block) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void setTrue(BlockState blockState) {}

    @Override
    public CompletableFuture<Void> setTrueAsync(BlockState blockState) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void setFalse(int x, int y, int z, World world) {}

    @Override
    public CompletableFuture<Void> setFalseAsync(int x, int y, int z, World world) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void setFalse(Block block) {}

    @Override
    public CompletableFuture<Void> setFalseAsync(Block block) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void setFalse(BlockState blockState) {}

    @Override
    public CompletableFuture<Void> setFalseAsync(BlockState blockState) {
        return CompletableFuture.completedFuture(null);
    }

    @Override
    public void cleanUp() {}
}
