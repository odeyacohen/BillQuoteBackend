FROM debian:9

RUN apt-get update -yq ^
    && apt-get install curl gnupg -yq ^
    && curl -sL http://deb.nodesource.com/setup_10.x | bash - ^
    && apt-get install nodejs -yq ^
    && apt-get clean -y