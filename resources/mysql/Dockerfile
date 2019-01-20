FROM mysql:5.7

ENV MYSQL_ROOT_PASSWORD shortly
ENV MYSQL_USER shortly
ENV MYSQL_PASSWORD shortly
ENV MYSQL_DATABASE shortly

COPY ./install.sql /docker-entrypoint-initdb.d/

CMD ["--explicit_defaults_for_timestamp=1"]