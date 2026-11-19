-- Insertar múltiples reservas por sala (4-5 reservas por sala)

-- Sala de Conferencias A (5 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias A'), 'Juan Pérez', '2026-11-26 09:00:00', '2026-11-26 10:30:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias A'), 'Hector Valdez', '2026-11-26 11:00:00', '2026-11-26 12:30:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias A'), 'Roxana Silva', '2026-11-26 14:00:00', '2026-11-26 15:30:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias A'), 'Felipe Morales', '2026-11-27 09:00:00', '2026-11-27 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias A'), 'Lorena Parra', '2026-11-28 10:00:00', '2026-11-28 11:30:00', false);

-- Sala de Conferencias B (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias B'), 'María García', '2026-11-26 10:00:00', '2026-11-26 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias B'), 'Vicente López', '2026-11-26 14:00:00', '2026-11-26 15:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias B'), 'Tamara Bravo', '2026-11-27 10:00:00', '2026-11-27 11:30:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias B'), 'Salvador Cortés', '2026-11-28 09:00:00', '2026-11-28 10:00:00', true);

-- Sala de Conferencias C (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias C'), 'Carlos López', '2026-11-26 11:00:00', '2026-11-26 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias C'), 'Rodrigo Cabrera', '2026-11-26 15:00:00', '2026-11-26 16:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias C'), 'Natalia Ibáñez', '2026-11-27 11:00:00', '2026-11-27 12:30:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Conferencias C'), 'Eugenio Torres', '2026-11-28 14:00:00', '2026-11-28 15:00:00', false);

-- Sala de Juntas 1 (5 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 1'), 'Ana Martínez', '2026-11-26 08:00:00', '2026-11-26 09:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 1'), 'Guillermo Espinoza', '2026-11-26 10:00:00', '2026-11-26 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 1'), 'Patricia Valencia', '2026-11-26 13:00:00', '2026-11-26 14:00:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 1'), 'Alonso Riquelme', '2026-11-27 09:00:00', '2026-11-27 10:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 1'), 'Marcela Donoso', '2026-11-28 11:00:00', '2026-11-28 12:00:00', false);

-- Sala de Juntas 2 (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 2'), 'Roberto Flores', '2026-11-26 09:00:00', '2026-11-26 10:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 2'), 'Catalina Pino', '2026-11-26 11:00:00', '2026-11-26 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 2'), 'Ernesto Reyes', '2026-11-27 14:00:00', '2026-11-27 15:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Juntas 2'), 'Verónica Fuentes', '2026-11-28 10:00:00', '2026-11-28 11:00:00', true);

-- Sala de Capacitación (5 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Capacitación'), 'Laura Rodríguez', '2026-11-26 09:00:00', '2026-11-26 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Capacitación'), 'Fabricio Aceituno', '2026-11-26 13:00:00', '2026-11-26 16:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Capacitación'), 'Roxana Muñoz', '2026-11-27 09:00:00', '2026-11-27 12:00:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Capacitación'), 'Edmundo Riquelme', '2026-11-28 09:00:00', '2026-11-28 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Capacitación'), 'Marisa Sepúlveda', '2026-11-29 10:00:00', '2026-11-29 13:00:00', false);

-- Auditorio Principal (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Auditorio Principal'), 'Pedro Díaz', '2026-11-26 10:00:00', '2026-11-26 13:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Auditorio Principal'), 'Horacio Mena', '2026-11-26 14:00:00', '2026-11-26 17:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Auditorio Principal'), 'Nidia Vásquez', '2026-11-27 10:00:00', '2026-11-27 13:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Auditorio Principal'), 'Benito Ibáñez', '2026-11-28 09:00:00', '2026-11-28 12:00:00', true);

-- Auditorio Secundario (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Auditorio Secundario'), 'Sofía Álvarez', '2026-11-26 10:00:00', '2026-11-26 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Auditorio Secundario'), 'Hilarión Ponce', '2026-11-26 14:00:00', '2026-11-26 16:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Auditorio Secundario'), 'Lorena Castro', '2026-11-27 11:00:00', '2026-11-27 13:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Auditorio Secundario'), 'Salvador Vega', '2026-11-28 10:00:00', '2026-11-28 12:00:00', false);

-- Oficina Privada 1 (5 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 1'), 'Miguel Torres', '2026-11-26 08:30:00', '2026-11-26 09:30:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 1'), 'Vicente Riquelme', '2026-11-26 10:00:00', '2026-11-26 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 1'), 'Margarita Flores', '2026-11-26 14:00:00', '2026-11-26 15:00:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 1'), 'Ernesto Vásquez', '2026-11-27 09:00:00', '2026-11-27 10:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 1'), 'Patricia Mena', '2026-11-28 10:00:00', '2026-11-28 11:00:00', false);

-- Oficina Privada 2 (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 2'), 'Elena Sánchez', '2026-11-26 09:00:00', '2026-11-26 10:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 2'), 'Orlando Bravo', '2026-11-26 11:00:00', '2026-11-26 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 2'), 'Roxana González', '2026-11-27 10:00:00', '2026-11-27 11:00:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Oficina Privada 2'), 'Leopoldo Silva', '2026-11-28 09:00:00', '2026-11-28 10:00:00', false);

-- Espacio Coworking A (5 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking A'), 'Javier Ruiz', '2026-11-26 08:00:00', '2026-11-26 17:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking A'), 'Marisa López', '2026-11-27 08:00:00', '2026-11-27 17:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking A'), 'Fabricio Vásquez', '2026-11-28 08:00:00', '2026-11-28 17:00:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking A'), 'Nidia Bravo', '2026-11-29 08:00:00', '2026-11-29 17:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking A'), 'Benito López', '2026-11-30 08:00:00', '2026-11-30 17:00:00', false);

-- Espacio Coworking B (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking B'), 'Gabriela Moreno', '2026-11-26 08:00:00', '2026-11-26 16:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking B'), 'Horacio Parra', '2026-11-27 08:00:00', '2026-11-27 16:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking B'), 'Tamara Espinoza', '2026-11-28 08:00:00', '2026-11-28 16:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Espacio Coworking B'), 'Edmundo Flores', '2026-11-29 08:00:00', '2026-11-29 16:00:00', true);

-- Sala de Descanso (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Descanso'), 'Oscar Jiménez', '2026-11-26 12:00:00', '2026-11-26 13:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Descanso'), 'Lorena Riquelme', '2026-11-26 13:00:00', '2026-11-26 14:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Descanso'), 'Alonso Pino', '2026-11-27 12:00:00', '2026-11-27 13:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Descanso'), 'Verónica Cabrera', '2026-11-28 12:00:00', '2026-11-28 13:00:00', false);

-- Cafetería (5 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Cafetería'), 'Valentina Cruz', '2026-11-26 12:00:00', '2026-11-26 13:30:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Cafetería'), 'Guillermo Vásquez', '2026-11-26 13:30:00', '2026-11-26 15:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Cafetería'), 'Margarita Bravo', '2026-11-27 12:00:00', '2026-11-27 13:30:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Cafetería'), 'Salvador Aceituno', '2026-11-28 12:00:00', '2026-11-28 13:30:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Cafetería'), 'Patricia González', '2026-11-29 12:00:00', '2026-11-29 13:30:00', false);

-- Área de Recepción (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Área de Recepción'), 'Marcos Vega', '2026-11-26 09:00:00', '2026-11-26 10:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Área de Recepción'), 'Roxana Mena', '2026-11-26 11:00:00', '2026-11-26 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Área de Recepción'), 'Ernesto Ibáñez', '2026-11-27 09:00:00', '2026-11-27 10:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Área de Recepción'), 'Nidia Ramos', '2026-11-28 10:00:00', '2026-11-28 11:00:00', true);

-- Sala de Servidores (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Servidores'), 'Hilarión Flores', '2026-11-26 10:00:00', '2026-11-26 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Servidores'), 'Tamara Vásquez', '2026-11-26 14:00:00', '2026-11-26 15:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Servidores'), 'Benito Morales', '2026-11-27 10:00:00', '2026-11-27 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Servidores'), 'Lorena Sepúlveda', '2026-11-28 09:00:00', '2026-11-28 10:00:00', false);

-- Almacén General (5 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Almacén General'), 'Vicente Bravo', '2026-11-26 11:00:00', '2026-11-26 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Almacén General'), 'Marisa Parra', '2026-11-26 14:00:00', '2026-11-26 15:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Almacén General'), 'Alonso Ramos', '2026-11-27 11:00:00', '2026-11-27 12:00:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Almacén General'), 'Patricia López', '2026-11-28 11:00:00', '2026-11-28 12:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Almacén General'), 'Orlando Vega', '2026-11-29 11:00:00', '2026-11-29 12:00:00', false);

-- Sala de Archivo (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Archivo'), 'Beatriz López', '2026-11-26 10:00:00', '2026-11-26 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Archivo'), 'Edmundo Cabrera', '2026-11-26 14:00:00', '2026-11-26 15:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Archivo'), 'Roxana Flores', '2026-11-27 10:00:00', '2026-11-27 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Archivo'), 'Leopoldo Pino', '2026-11-28 14:00:00', '2026-11-28 15:00:00', true);

-- Estudio de Fotografía (5 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Estudio de Fotografía'), 'Diego Ponce', '2026-11-26 10:00:00', '2026-11-26 13:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Estudio de Fotografía'), 'Margarita Vásquez', '2026-11-26 14:00:00', '2026-11-26 17:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Estudio de Fotografía'), 'Salvador Riquelme', '2026-11-27 10:00:00', '2026-11-27 13:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Estudio de Fotografía'), 'Verónica Espinoza', '2026-11-28 10:00:00', '2026-11-28 13:00:00', true),
((SELECT id_room FROM public.rooms WHERE name = 'Estudio de Fotografía'), 'Horacio Aceituno', '2026-11-29 14:00:00', '2026-11-29 17:00:00', false);

-- Sala de Descanso VIP (4 reservas)
INSERT INTO public.bookings (id_room, responsable, date_start, date_end, cancelled) VALUES
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Descanso VIP'), 'Vicente Ramos', '2026-11-26 10:00:00', '2026-11-26 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Descanso VIP'), 'Nidia Muñoz', '2026-11-26 15:00:00', '2026-11-26 16:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Descanso VIP'), 'Benito Rodríguez', '2026-11-27 10:00:00', '2026-11-27 11:00:00', false),
((SELECT id_room FROM public.rooms WHERE name = 'Sala de Descanso VIP'), 'Tamara Bravo', '2026-11-28 15:00:00', '2026-11-28 16:00:00', false);