package com.hashini.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.file.Path;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItemList;
    private OnCartItemChangeListener onCartItemChangeListener;

    public interface OnCartItemChangeListener {
        void onCartItemChanged();
    }

    public CartAdapter(List<CartItem> cartItemList, OnCartItemChangeListener onCartItemChangeListener) {
        this.cartItemList = cartItemList;
        this.onCartItemChangeListener = onCartItemChangeListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItemList.get(position);
        holder.productName.setText(cartItem.getProduct().getName());
        holder.productPrice.setText("$" + cartItem.getProduct().getPrice());
        holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));
        holder.productImage.setImageResource(cartItem.getProduct().getImageResId());

        holder.increaseQuantity.setOnClickListener(v -> {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));
            onCartItemChangeListener.onCartItemChanged();
        });

        holder.decreaseQuantity.setOnClickListener(v -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));
                onCartItemChangeListener.onCartItemChanged();
            }
        });

        holder.removeButton.setOnClickListener(v -> {
            cartItemList.remove(position);
            notifyItemRemoved(position);
            onCartItemChangeListener.onCartItemChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView productQuantity;
        Button increaseQuantity;
        Button decreaseQuantity;
        Button removeButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            increaseQuantity = itemView.findViewById(R.id.increaseQuantity);
            decreaseQuantity = itemView.findViewById(R.id.decreaseQuantity);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }

    private class CartItem {
        public Path getProduct() {
            Path o = null;
            return o;
        }
    }
}