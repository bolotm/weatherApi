package com.example.weatherapibyme.commonn;

import androidx.annotation.NonNull;

public class Resource<N> {

    @NonNull
    public final String message;
    public final N data;
    public final Status status;


    public Resource(@NonNull Status status, N data,String message) {
        this.message = message;
        this.data = data;
        this.status = status;
    }

    public static <N> Resource<N> success(N data) {
        return new Resource<>(Status.SUCCESSFUL,data,null);
    }
    public static <N> Resource<N> loading() {
        return new Resource<>(Status.LOADING,null,null);
    }

    public static <N> Resource<N> error(N data,String message) {
        return new Resource<>(Status.ERROR,data,message);
    }

    public enum Status{
        SUCCESSFUL,LOADING,ERROR
    }
}
