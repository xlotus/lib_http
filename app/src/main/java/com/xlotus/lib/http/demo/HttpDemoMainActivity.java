package com.xlotus.lib.http.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.xlotus.lib.http.demo.databinding.ActivityMainBinding;
import com.xlotus.lib.http.demo.http.SwaggerApi;
import com.xlotus.lib.http.demo.model.ModelPet;

import java.util.ArrayList;

public class HttpDemoMainActivity extends BaseActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnAddPet.setOnClickListener(this);
        binding.btnPetList.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == binding.btnAddPet.getId()) {
            addPet();
        } else if (id == binding.btnPetList.getId()) {
            startActivity(new Intent(this, PetListActivity.class));
        }
    }

    private void addPet() {
        showLoading();
        ModelPet pet = createPet();
        SwaggerApi.addPet(pet, responseWrapper -> {
            hideLoading();
            Logger.d(responseWrapper);
            if (responseWrapper.isSuccessful()) {
                Toast.makeText(HttpDemoMainActivity.this, R.string.tip_success, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(HttpDemoMainActivity.this, responseWrapper.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ModelPet createPet() {
        ModelPet pet = new ModelPet();
        pet.id = 0;
        pet.name = "doggie";
        pet.category = new ModelPet.Category();
        pet.category.id = 0;
        pet.category.name = "string";
        pet.photoUrls = new ArrayList<>();
        pet.photoUrls.add("string");
        ModelPet.Tag tag = new ModelPet.Tag();
        tag.id = 0;
        tag.name = "string";
        pet.tags = new ArrayList<>();
        pet.tags.add(tag);
        pet.status = "available";
        return pet;
    }
}
