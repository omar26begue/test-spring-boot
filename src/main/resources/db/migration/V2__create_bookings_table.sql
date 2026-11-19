CREATE TABLE IF NOT EXISTS public.bookings (
    id_booking UUID NOT NULL DEFAULT gen_random_uuid(),
    id_room UUID NOT NULL,
    responsable VARCHAR(100) NOT NULL,
    date_start TIMESTAMP NOT NULL,
    date_end TIMESTAMP NOT NULL,
    cancelled BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_booking),
    FOREIGN KEY (id_room) REFERENCES public.rooms(id_room) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_bookings_room_id ON public.bookings(id_room);
CREATE INDEX IF NOT EXISTS idx_bookings_date_range ON public.bookings(date_start, date_end);
CREATE INDEX IF NOT EXISTS idx_bookings_cancelled ON public.bookings(cancelled);