package com.hashini.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class BlogPost extends RecyclerView.Adapter<BlogPost.BlogPostViewHolder> {

    private List<BlogPost> blogPostList;

    public BlogPost(List<BlogPost> blogPostList) {
        this.blogPostList = blogPostList;
    }

    @NonNull
    @Override
    public BlogPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog_post, parent, false);
        return new BlogPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogPostViewHolder holder, int position) {
        BlogPost blogPost = blogPostList.get(position);
        holder.titleTextView.setText(blogPost.getTitle());
        holder.contentTextView.setText(blogPost.getContent());
    }

    private int getContent() {
        return 0;

    }

    private int getTitle() {
        return 0;
    }

    @Override
    public int getItemCount() {
        return blogPostList.size();
    }

    public static class BlogPostViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView contentTextView;

        public BlogPostViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            contentTextView = itemView.findViewById(R.id.contentTextView);
        }
    }
}
