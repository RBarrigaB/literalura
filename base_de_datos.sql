create table autor (
id_autor bigserial primary key,
nombre varchar(200) not null,
anio_nacimiento integer not null,
anio_fallecimiento integer
);


create table libro (
id_libro bigserial primary key,
titulo varchar(200) not null,
idioma varchar(5) not null,
num_descargas int not null default 0,
id_autor bigint not null
);

alter table libro
add constraint fk_libro_autor
foreign key (id_autor)
references autor(id_autor)
on delete cascade;

alter table autor
add constraint uk_autor_nombre_nacimiento
unique (nombre, anio_nacimiento);