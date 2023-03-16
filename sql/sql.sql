create table cilicili.producer
(
    id           char(19)                                               not null comment '创作者ID'
        primary key,
    name         varchar(20)                                            not null comment '创作者姓名',
    intro        varchar(500)     default '这个人很懒，没有留下个人简介' not null comment '创作者简介',
    avatar       varchar(255)                                           null comment '创作者头像',
    sort         int unsigned     default '0'                           not null comment '排序',
    is_deleted   tinyint unsigned default '0'                           not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                                               not null comment '创建时间',
    gmt_modified datetime                                               not null comment '更新时间',
    constraint uk_name
        unique (name)
)
    comment '创作者';

create table cilicili.classification
(
    id           char(19)                 not null comment '类别ID'
        primary key,
    title        varchar(10)              not null comment '类别名称',
    parent_id    char(19)     default '0' not null comment '父ID',
    sort         int unsigned default '0' not null comment '排序字段',
    gmt_create   datetime                 not null comment '创建时间',
    gmt_modified datetime                 not null comment '更新时间'
)
    comment '影视类别';

create index idx_parent_id
    on cilicili.classification (parent_id);


INSERT INTO cilicili.producer (id, name, intro, avatar, sort, is_deleted, gmt_create, gmt_modified) VALUES ('1', '1', '1', '1', 1, 1, '2023-02-28 18:08:39', '2023-02-28 18:08:41');
INSERT INTO cilicili.producer (id, name, intro, avatar, sort, is_deleted, gmt_create, gmt_modified) VALUES ('2', '2', '2', '2', 2, 0, '2023-02-28 22:34:13', '2023-02-28 22:34:14');
