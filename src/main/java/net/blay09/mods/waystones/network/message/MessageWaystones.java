package net.blay09.mods.waystones.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.blay09.mods.waystones.util.WaystoneEntry;

public class MessageWaystones implements IMessage {

	private WaystoneEntry[] entries;
	private WaystoneEntry[] serverEntries;
	private long lastFreeWarp;
	private long lastWarpStoneUse;

	public MessageWaystones() {
	}

	public MessageWaystones(WaystoneEntry[] entries, WaystoneEntry[] serverEntries, long lastFreeWarp, long lastWarpStoneUse) {
		this.entries = entries;
		this.serverEntries = serverEntries;
		this.lastFreeWarp = lastFreeWarp;
		this.lastWarpStoneUse = lastWarpStoneUse;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entries = new WaystoneEntry[buf.readByte()];
		for(int i = 0; i < entries.length; i++) {
			entries[i] = WaystoneEntry.read(buf);
		}
		serverEntries = new WaystoneEntry[buf.readByte()];
		for(int i = 0; i < serverEntries.length; i++) {
			serverEntries[i] = WaystoneEntry.read(buf);
			serverEntries[i].setGlobal(true);
		}
		lastFreeWarp = buf.readLong();
		lastWarpStoneUse = buf.readLong();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeByte(entries.length);
		for (WaystoneEntry entry : entries) {
			entry.write(buf);
		}
		buf.writeByte(serverEntries.length);
		for(WaystoneEntry entry : serverEntries) {
			entry.write(buf);
		}
		buf.writeLong(lastFreeWarp);
		buf.writeLong(lastWarpStoneUse);
	}

	public WaystoneEntry[] getEntries() {
		return entries;
	}

	public WaystoneEntry[] getServerEntries() {
		return serverEntries;
	}

	public long getLastFreeWarp() {
		return lastFreeWarp;
	}

	public long getLastWarpStoneUse() {
		return lastWarpStoneUse;
	}
}
