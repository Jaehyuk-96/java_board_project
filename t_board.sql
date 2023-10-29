//sql tboard

create table t_board (
    bno            int             primary key auto_increment,
    btitle          varchar(20)    not null,
    bcontent       varchar(200)     not null,
    bwriter         varchar(13)     not null,
    bdate			datetime		not null
);



insert into t_board set `btitle` = '제목', `bcontent`='내용', `bwriter`='작성자';




