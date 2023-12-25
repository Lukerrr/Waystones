package net.blay09.mods.waystones.api.event;

import net.blay09.mods.balm.api.event.BalmEvent;
import net.blay09.mods.waystones.api.Waystone;

public class WaystoneRemovedEvent extends BalmEvent {
    private final Waystone waystone;

    public WaystoneRemovedEvent(Waystone waystone) {
        this.waystone = waystone;
    }

    public Waystone getWaystone() {
        return waystone;
    }
}
