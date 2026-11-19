CREATE TABLE IF NOT EXISTS public.rooms (
    id_room UUID NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_room)
);

CREATE INDEX IF NOT EXISTS idx_rooms_name ON public.rooms(name);