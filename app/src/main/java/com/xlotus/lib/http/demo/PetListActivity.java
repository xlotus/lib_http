package com.xlotus.lib.http.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xlotus.lib.http.INetListener;
import com.xlotus.lib.http.ResponseWrapper;
import com.xlotus.lib.http.demo.databinding.ActivityPetListBinding;
import com.xlotus.lib.http.demo.databinding.ItemPetBinding;
import com.xlotus.lib.http.demo.http.SwaggerApi;
import com.xlotus.lib.http.demo.model.ModelPet;

import java.util.ArrayList;
import java.util.List;

public class PetListActivity extends BaseActivity {

    private RecyclerView.LayoutManager layoutManager;
    private PetAdapter adapter;

    private ActivityPetListBinding binding;

    private final List<ModelPet> pets = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPetListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        layoutManager = new LinearLayoutManager(this);
        adapter = new PetAdapter(pets);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

        showLoading();
        loadPets();
    }

    private void loadPets() {
        SwaggerApi.getPets("available", new INetListener<List<ModelPet>>() {
            @Override
            public void onResponse(ResponseWrapper<List<ModelPet>> responseWrapper) {
                hideLoading();
                PetListActivity.this.pets.clear();
                if (responseWrapper.isSuccessful()) {
                    PetListActivity.this.pets.addAll(responseWrapper.getData());
                }
                PetListActivity.this.adapter.notifyDataSetChanged();
            }
        });


    }

    private static class PetAdapter extends RecyclerView.Adapter<PetHolder> {
        private final List<ModelPet> pets;

        public PetAdapter(List<ModelPet> pets) {
            this.pets = pets;
        }

        @NonNull
        @Override
        public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PetHolder(ItemPetBinding.inflate(LayoutInflater.from(parent.getContext())));
        }

        @Override
        public void onBindViewHolder(@NonNull PetHolder holder, int position) {
            holder.bind(pets.get(position));
        }

        @Override
        public int getItemCount() {
            return pets == null ? 0 : pets.size();
        }
    }

    private static class PetHolder extends RecyclerView.ViewHolder {
        private ItemPetBinding binding;

        public PetHolder(@NonNull ItemPetBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ModelPet pet) {
            binding.tvPetName.setText(pet.name);
            binding.tvPetId.setText(String.valueOf(pet.id));
        }
    }
}
