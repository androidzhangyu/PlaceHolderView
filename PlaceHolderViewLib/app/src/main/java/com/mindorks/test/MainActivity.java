package com.mindorks.test;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.mindorks.butterknifelite.ButterKnifeLite;
import com.mindorks.butterknifelite.annotations.BindView;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.test.drawer.DrawerHeader;
import com.mindorks.test.drawer.DrawerMenuItem;
import com.mindorks.test.gallery.ImageTypeBig;
import com.mindorks.test.gallery.ImageTypeSmall;
import com.mindorks.test.gallery.ImageTypeSmallPlaceHolder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawerView)
    PlaceHolderView mDrawerView;

    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.galleryView)
    PlaceHolderView mGalleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnifeLite.bind(this);
//        mDrawerView.getBuilder()
//                .setHasFixedSize(false)
//                .setItemViewCacheSize(10);
//                .setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//                .setLayoutManager(new GridLayoutManager(this, 3));
        setupDrawer();
        setupGallery();

    }

    private void setupDrawer(){
        mDrawerView
                .addView(new DrawerHeader())
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_PROFILE))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_REQUESTS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_MESSAGE))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_GROUPS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_NOTIFICATIONS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_TERMS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_SETTINGS))
                .addView(new DrawerMenuItem(this.getApplicationContext(), DrawerMenuItem.DRAWER_MENU_ITEM_LOGOUT));

        ActionBarDrawerToggle  drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void setupGallery(){
        List<Image> imageList = Utils.loadImages(this.getApplicationContext());
        List<Image> newImageList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            newImageList.add(imageList.get(i));
        }
        mGalleryView.addView(new ImageTypeSmallPlaceHolder(this.getApplicationContext(), newImageList));

        for (int i = imageList.size() - 1; i >= 0; i--) {
            mGalleryView.addView(new ImageTypeBig(this.getApplicationContext(), mGalleryView, imageList.get(i).getUrl()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnifeLite.unbind(this);
    }
}
