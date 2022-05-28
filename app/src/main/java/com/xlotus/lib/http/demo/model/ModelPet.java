package com.xlotus.lib.http.demo.model;

import androidx.annotation.NonNull;

import java.util.List;

public class ModelPet {
    public long id;
    public String name;
    public String status;
    public Category category;
    public List<Tag> tags;
    public List<String> photoUrls;

    public static class Category {
        public int id;
        public String name;

        @NonNull
        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class Tag {
        public int id;
        public String name;

        @NonNull
        @Override
        public String toString() {
            return "Tag{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "ModelPet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", category=" + category +
                ", tags=" + tags +
                '}';
    }
}
