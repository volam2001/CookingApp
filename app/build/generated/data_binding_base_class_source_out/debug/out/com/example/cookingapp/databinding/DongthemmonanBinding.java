// Generated by view binder compiler. Do not edit!
package com.example.cookingapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.cookingapp.R;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class DongthemmonanBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btncancle;

  @NonNull
  public final Button btnthemmonan;

  @NonNull
  public final ImageView hinhanh;

  @NonNull
  public final LinearLayout itemLayout;

  @NonNull
  public final TextInputEditText txtnguyenlieu;

  @NonNull
  public final TextInputEditText txttendaubep;

  @NonNull
  public final TextInputEditText txttenmonan;

  private DongthemmonanBinding(@NonNull LinearLayout rootView, @NonNull Button btncancle,
      @NonNull Button btnthemmonan, @NonNull ImageView hinhanh, @NonNull LinearLayout itemLayout,
      @NonNull TextInputEditText txtnguyenlieu, @NonNull TextInputEditText txttendaubep,
      @NonNull TextInputEditText txttenmonan) {
    this.rootView = rootView;
    this.btncancle = btncancle;
    this.btnthemmonan = btnthemmonan;
    this.hinhanh = hinhanh;
    this.itemLayout = itemLayout;
    this.txtnguyenlieu = txtnguyenlieu;
    this.txttendaubep = txttendaubep;
    this.txttenmonan = txttenmonan;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static DongthemmonanBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static DongthemmonanBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.dongthemmonan, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static DongthemmonanBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btncancle;
      Button btncancle = ViewBindings.findChildViewById(rootView, id);
      if (btncancle == null) {
        break missingId;
      }

      id = R.id.btnthemmonan;
      Button btnthemmonan = ViewBindings.findChildViewById(rootView, id);
      if (btnthemmonan == null) {
        break missingId;
      }

      id = R.id.hinhanh;
      ImageView hinhanh = ViewBindings.findChildViewById(rootView, id);
      if (hinhanh == null) {
        break missingId;
      }

      LinearLayout itemLayout = (LinearLayout) rootView;

      id = R.id.txtnguyenlieu;
      TextInputEditText txtnguyenlieu = ViewBindings.findChildViewById(rootView, id);
      if (txtnguyenlieu == null) {
        break missingId;
      }

      id = R.id.txttendaubep;
      TextInputEditText txttendaubep = ViewBindings.findChildViewById(rootView, id);
      if (txttendaubep == null) {
        break missingId;
      }

      id = R.id.txttenmonan;
      TextInputEditText txttenmonan = ViewBindings.findChildViewById(rootView, id);
      if (txttenmonan == null) {
        break missingId;
      }

      return new DongthemmonanBinding((LinearLayout) rootView, btncancle, btnthemmonan, hinhanh,
          itemLayout, txtnguyenlieu, txttendaubep, txttenmonan);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
