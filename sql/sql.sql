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

create table cilicili.video
(
    id                       char(19)                                not null comment '影视ID'
        primary key,
    producer_id              char(19)                                not null comment '创作者ID',
    classification_id        char(19)                                not null comment '分类ID',
    classification_parent_id char(19)                                not null comment '分类父级ID',
    title                    varchar(50)                             not null comment '影视标题',
    price                    decimal(10, 2) unsigned default 0.00    not null comment '影视销售价格，设置为0则可免费观看',
    episode_num              int unsigned            default '0'     not null comment '总集数',
    cover                    varchar(255) charset utf8mb3            not null comment '影视封面图片路径',
    buy_count                bigint unsigned         default '0'     not null comment '销售数量',
    view_count               bigint unsigned         default '0'     not null comment '浏览数量',
    version                  bigint unsigned         default '1'     not null comment '乐观锁',
    status                   varchar(10)             default 'Draft' not null comment '影视状态 Draft未发布  Normal已发布',
    is_deleted               tinyint                 default 0       null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create               datetime                                not null comment '创建时间',
    gmt_modified             datetime                                not null comment '更新时间'
)
    comment '影视';

create index idx_classification_id
    on cilicili.video (classification_id);

create index idx_producer_id
    on cilicili.video (producer_id);

create index idx_title
    on cilicili.video (title);


    
create table cilicili.video_description
(
    id           char(19) not null comment '影视ID'
        primary key,
    description  text     null comment '影视简介',
    gmt_create   datetime not null comment '创建时间',
    gmt_modified datetime not null comment '更新时间'
)
    comment '影视简介';




INSERT INTO cilicili.producer (id, name, intro, avatar, sort, is_deleted, gmt_create, gmt_modified) VALUES ('1', '1', '1', '1', 1, 1, '2023-02-28 18:08:39', '2023-02-28 18:08:41');
INSERT INTO cilicili.producer (id, name, intro, avatar, sort, is_deleted, gmt_create, gmt_modified) VALUES ('2', '2', '2', '2', 2, 0, '2023-02-28 22:34:13', '2023-02-28 22:34:14');
