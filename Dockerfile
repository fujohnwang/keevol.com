FROM ubuntu:latest

MAINTAINER afoo <afoo@afoo.me>

# install oracle java8 and set JAVA_HOME env
RUN add-apt-repository ppa:webupd8team/java
RUN apt-get update
RUN apt-get -y upgrade
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get -y install oracle-java8-installer && apt-get clean
RUN update-alternatives --display java
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

CMD ["java", "-version"]

