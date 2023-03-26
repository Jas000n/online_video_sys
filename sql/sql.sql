create table classification
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
    on classification (parent_id);

create table episode
(
    id                  char(19)                         not null comment '集ID'
        primary key,
    video_id            char(19)                         not null comment '影视ID',
    season_id           char(19)                         not null comment '季ID',
    title               varchar(50)                      not null comment '集名称',
    video_source_id     varchar(100)                     null comment '云端视频资源',
    video_original_name varchar(100)                     null comment '原始文件名称',
    sort                int unsigned     default '0'     not null comment '排序字段',
    play_count          bigint unsigned  default '0'     not null comment '播放次数',
    is_free             tinyint unsigned default '0'     not null comment '是否可以试听：0收费 1免费',
    duration            float            default 0       not null comment '视频时长（秒）',
    status              varchar(20)      default 'Empty' not null comment 'Empty未上传 Transcoding转码中  Normal正常',
    size                bigint unsigned  default '0'     not null comment '视频源文件大小（字节）',
    version             bigint unsigned  default '1'     not null comment '乐观锁',
    gmt_create          datetime                         not null comment '创建时间',
    gmt_modified        datetime                         not null comment '更新时间'
)
    comment '每一集视频';

create index idx_season_id
    on episode (season_id);

create index idx_video_id
    on episode (video_id);

create table producer
(
    id           char(19)                                               not null comment '创作者ID'
        primary key,
    name         varchar(20)                                            not null comment '创作者姓名',
    intro        varchar(500)     default '这个人很懒，没有留下个人简介' not null comment '创作者简介',
    avatar       varchar(255)                                           null comment '创作者头像',
    sort         int unsigned     default '0'                           not null comment '排序',
    is_deleted   tinyint unsigned default '0'                           not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                                               not null comment '创建时间',
    gmt_modified datetime                                               not null comment '更新时间'
)
    comment '创作者';

create table season
(
    id           char(19)                 not null comment '季ID'
        primary key,
    video_id     char(19)                 not null comment '影视ID',
    title        varchar(50)              not null comment '季名称',
    sort         int unsigned default '0' not null comment '显示排序',
    gmt_create   datetime                 not null comment '创建时间',
    gmt_modified datetime                 not null comment '更新时间'
)
    comment '季';

create index idx_video_id
    on season (video_id);

create table video
(
    id                       char(19)                                not null comment '影视ID'
        primary key,
    producer_id              char(19)                                not null comment '创作者ID',
    classification_id        char(19)                                not null comment '分类ID',
    classification_parent_id char(19)                                null comment '分类父级ID',
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
    on video (classification_id);

create index idx_producer_id
    on video (producer_id);

create index idx_title
    on video (title);

create table video_description
(
    id           char(19) not null comment '影视ID'
        primary key,
    description  text     null comment '影视简介',
    gmt_create   datetime not null comment '创建时间',
    gmt_modified datetime not null comment '更新时间'
)
    comment '影视简介';

