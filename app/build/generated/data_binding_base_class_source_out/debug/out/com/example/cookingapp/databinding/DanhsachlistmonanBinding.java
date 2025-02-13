// Generated by view binder compiler. Do not edit!
package com.example.cookingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.cookingapp.R;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DanhsachlistmonanBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnHome;

  @NonNull
  public final LinearLayout drawerlayout;

  @NonNull
  public final NavigationView navigationview;

  @NonNull
  public final RecyclerView rcvdongdanhsach;

  private DanhsachlistmonanBinding(@NonNull LinearLayout rootView, @NonNull Button btnHome,
      @NonNull LinearLayout drawerlayout, @NonNull NavigationView navigationview,
      @NonNull RecyclerView rcvdongdanhsach) {
    this.rootView = rootView;
    this.btnHome = btnHome;
    this.drawerlayout = drawerlayout;
    this.navigationview = navigationview;
    this.rcvdongdanhsach = rcvdongdanhsach;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DanhsachlistmonanBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DanhsachlistmonanBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.danhsachlistmonan, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DanhsachlistmonanBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnHome;
      Button btnHome = ViewBindings.findChildViewById(rootView, id);
      if (btnHome == null) {
        break missingId;
      }

      LinearLayout drawerlayout = (LinearLayout) rootView;

      id = R.id.navigationview;
      NavigationView navigationview = ViewBindings.findChildViewById(rootView, id);
      if (navigationview == null) {
        break missingId;
      }

      id = R.id.rcvdongdanhsach;
      RecyclerView rcvdongdanhsach = ViewBindings.findChildViewById(rootView, id);
      if (rcvdongdanhsach == null) {
        break missingId;
      }

      return new DanhsachlistmonanBinding((LinearLayout) rootView, btnHome, drawerlayout,
          navigationview, rcvdongdanhsach);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
