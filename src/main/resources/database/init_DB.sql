drop table if exists PagesInfo; commit;

create table PagesInfo(
                          id SERIAL,
                          slug varchar,
                          title varchar,
                          description varchar,
                          html_content varchar,
                          content varchar,
                          published_at timestamp,
                          published_fl boolean,
                          priority int,
                          update_at timestamp
);
commit;