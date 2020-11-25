package edu.eci.arep.loadbalancer.components.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Procfile {
    String mainClass, gitRepo, AppName,serverUrl;
    public Procfile(){}
    public Procfile(String mainClass, String gitRepository, String AppName) {
        this.mainClass = mainClass;
        this.gitRepo = gitRepository;
        this.AppName = AppName;
    }
    public String getMainClass() {

        return mainClass;
    }
    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }
    public String getGitRepo() {

        return gitRepo;
    }
    public void setGitRepo(String gitRepository) {
        this.gitRepo = gitRepository;
    }
    @JsonProperty("AppName")
    public String getAppName() {

        return AppName;
    }

    public void setAppName(String appName) {
        this.AppName = appName;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    @Override
    public String toString() {

        return "Procfile{" +
                "mainClass='" + mainClass + '\'' +
                ", gitRepository='" + gitRepo + '\'' +
                ", appName='" + AppName + '\'' +
                '}';
    }
}
