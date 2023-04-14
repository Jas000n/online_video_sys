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
    comment '影视类别' row_format = COMPACT;

create index idx_parent_id
    on classification (parent_id);

create table comment
(
    id           char(19)                     not null comment 'ID'
        primary key,
    episode_id   varchar(19)      default ''  not null comment '影视id',
    member_id    varchar(19)      default ''  not null comment '会员id',
    nickname     varchar(50)                  null comment '会员昵称',
    avatar       varchar(255)                 null comment '会员头像',
    content      varchar(500)                 null comment '评论内容',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间'
)
    comment '评论';

create index idx_member_id
    on comment (member_id);

create index idx_video_id
    on comment (episode_id);

create table crm_banner
(
    id           char(19)         default ''  not null comment 'ID'
        primary key,
    title        varchar(20)      default ''  null comment '标题',
    image_url    varchar(500)     default ''  not null comment '图片地址',
    link_url     varchar(500)     default ''  null comment '链接地址',
    sort         int unsigned     default '0' not null comment '排序',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间',
    constraint uk_name
        unique (title)
)
    comment '首页banner表';

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
    comment '每一集视频' row_format = COMPACT;

create index idx_season_id
    on episode (season_id);

create index idx_video_id
    on episode (video_id);

create table oorder
(
    id            char(19)         default ''   not null
        primary key,
    order_no      varchar(20)      default ''   not null comment '订单号',
    video_id      varchar(19)      default ''   not null comment '影视id',
    video_title   varchar(100)                  null comment '影视名称',
    video_cover   varchar(255)                  null comment '影视封面',
    producer_name varchar(20)                   null comment '创作者名称',
    member_id     varchar(19)      default ''   not null comment '会员id',
    nickname      varchar(50)                   null comment '会员昵称',
    mobile        varchar(11)                   null comment '会员手机',
    total_fee     decimal(10, 2)   default 0.01 null comment '订单金额（分）',
    pay_type      tinyint                       null comment '支付类型（1：微信 2：支付宝）',
    status        tinyint                       null comment '订单状态（0：未支付 1：已支付）',
    is_deleted    tinyint unsigned default '0'  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create    datetime                      not null comment '创建时间',
    gmt_modified  datetime                      not null comment '更新时间',
    constraint ux_order_no
        unique (order_no)
)
    comment '订单';

create index idx_member_id
    on oorder (member_id);

create index idx_video_id
    on oorder (video_id);

create table pay_log
(
    id             char(19)         default ''   not null
        primary key,
    order_no       varchar(20)      default ''   not null comment '订单号',
    pay_time       datetime                      null comment '支付完成时间',
    total_fee      decimal(10, 2)   default 0.01 null comment '支付金额（分）',
    transaction_id varchar(30)                   null comment '交易流水号',
    trade_state    char(20)                      null comment '交易状态',
    pay_type       tinyint          default 0    not null comment '支付类型（1：微信 2：支付宝）',
    attr           text                          null comment '其他属性',
    is_deleted     tinyint unsigned default '0'  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create     datetime                      not null comment '创建时间',
    gmt_modified   datetime                      not null comment '更新时间',
    constraint uk_order_no
        unique (order_no)
)
    comment '支付日志表';

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
    comment '季' row_format = COMPACT;

create index idx_video_id
    on season (video_id);

create table ucenter_member
(
    id           char(19)               not null comment '会员id'
        primary key,
    openid       varchar(128)           null comment '微信openid',
    mobile       varchar(11) default '' null comment '手机号',
    password     varchar(255)           null comment '密码',
    nickname     varchar(50)            null comment '昵称',
    sex          tinyint unsigned       null comment '性别 1 女，2 男',
    age          tinyint unsigned       null comment '年龄',
    avatar       varchar(255)           null comment '用户头像',
    sign         varchar(100)           null comment '用户签名',
    is_disabled  tinyint(1)  default 0  not null comment '是否禁用 1（true）已禁用，  0（false）未禁用',
    is_deleted   tinyint(1)  default 0  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime               not null comment '创建时间',
    gmt_modified datetime               not null comment '更新时间'
)
    comment '用户表';

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
    comment '影视' row_format = COMPACT;

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

