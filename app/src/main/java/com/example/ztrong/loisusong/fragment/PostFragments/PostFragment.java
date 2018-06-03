package com.example.ztrong.loisusong.fragment.PostFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ztrong.loisusong.R;
import com.example.ztrong.loisusong.adapter.PostsRecyclerAdapter;
import com.example.ztrong.loisusong.service.constant.Constant;
import com.example.ztrong.loisusong.service.utils.realm.RealmConfigs;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public abstract class PostFragment extends Fragment {

	@BindView(R.id.rv_home)
	RecyclerView recyclerView;

	private Realm realm;
	private PostsRecyclerAdapter postsRecyclerAdapter;
	private String typePost;
	protected RecyclerView.LayoutManager layoutManager;

	public static Fragment newInstance(String type) {
		switch (type) {
			case Constant.POST_ALL:
				return new PostAllFragment();
			case Constant.POST_VIETNAM:
				return new PostVietNamFragment();
			case Constant.POST_INTERNATIONAL:
				return new PostInternationalFragment();
			default:
				throw new Error("No Such Fragment");
		}
	}

	protected void setUpDatabase(String typePost) {
		this.typePost = typePost;
		realm = Realm.getInstance(RealmConfigs.getConfig(typePost));
		postsRecyclerAdapter.setDatabase(realm);
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Realm.init(Objects.requireNonNull(getContext()));
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_posts_home, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		layoutManager = new LinearLayoutManager(getContext());
		recyclerView.setLayoutManager(layoutManager);
	}
}
