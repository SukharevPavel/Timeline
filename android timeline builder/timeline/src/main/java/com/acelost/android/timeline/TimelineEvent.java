package com.acelost.android.timeline;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.concurrent.TimeUnit;

import static com.acelost.android.timeline.Preconditions.checkIsPositive;
import static com.acelost.android.timeline.Preconditions.checkNotEmpty;
import static com.acelost.android.timeline.Preconditions.checkNotNull;

public final class TimelineEvent {

    private final String name;
    private final String payload;
    private final TimeUnit units;
    private final long start;
    private final long end;
    private final int count;

    public TimelineEvent(@NonNull final String name,
                         @NonNull final TimeUnit units,
                         final long start,
                         final long end) {
        this(name, null, units, start, end, 1);
    }

    public TimelineEvent(@NonNull final String name,
                         @Nullable final String payload,
                         @NonNull final TimeUnit units,
                         final long start,
                         final long end) {
        this(name, payload, units, start, end, 1);
    }

    public TimelineEvent(@NonNull final String name,
                         @Nullable final String payload,
                         @NonNull final TimeUnit units,
                         final long start,
                         final long end,
                         final int count) {
        this.name = checkNotEmpty(name);
        this.payload = payload;
        this.units = checkNotNull(units);
        this.start = start;
        this.end = end;
        this.count = checkIsPositive(count);
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public String getPayload() {
        return payload;
    }

    @NonNull
    public TimeUnit getUnits() {
        return units;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public long getStartMillis() {
        return units.toMillis(start);
    }

    public long getEndMillis() {
        return units.toMillis(end);
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimelineEvent event = (TimelineEvent) o;

        if (getStartMillis() != event.getStartMillis()) return false;
        if (getEndMillis() != event.getEndMillis()) return false;
        if (getCount() != event.getCount()) return false;
        if (payload != null ? !payload.equals(event.payload) : event.payload != null) return false;
        return name.equals(event.name);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        result = 31 * result + (int) (getStartMillis() ^ (getStartMillis() >>> 32));
        result = 31 * result + (int) (getEndMillis() ^ (getEndMillis() >>> 32));
        result = 31 * result + count;
        return result;
    }

    @NonNull
    @Override
    public String toString() {
        return "TimelineEvent{" +
                "name='" + name + '\'' +
                ", payload=" + payload +
                ", units=" + units +
                ", start=" + start +
                ", end=" + end +
                ", count=" + count +
                '}';
    }

}
