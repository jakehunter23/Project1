package com.example.project1;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class ActurialApplicantRecAdapter extends RecyclerView.Adapter<ActurialApplicantRecAdapter.ActAptView> {
    @NonNull
    Context context;
    List<JobRequestModel> requestModelList;
    public ActurialApplicantRecAdapter(Context context, List<JobRequestModel> requestModelList){
       this.context=context;
       this.requestModelList=requestModelList;
    }
    public ActAptView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.acturial_applicant_rec_item,parent,false);
        return new ActAptView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActAptView holder, int position) {
        JobRequestModel item = requestModelList.get(position);
        holder.information.setText("Show More");
        holder.hiddenView.setVisibility(View.GONE);
        String firstname = item.getFirstName();
        String lastname = item.getLastName();
        holder.name.setText(firstname +" "+ lastname );
        String token = item.getToken();
        holder.options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu=new PopupMenu(context,holder.options);
                menu.inflate(R.menu.mainoptionmenu);

                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu1:
                                FcmNotificationsSender notificationsSender = new FcmNotificationsSender(token, context.getString(R.string.noti_title1), context.getString(R.string.noti_body1),context,context.getApplicationContext(),1 );
                                notificationsSender.SendNotifications();
                                break;
                            case R.id.menu2:
                                //handle menu2 click
                                break;
                            case R.id.menu3:
                                //handle menu3 click
                                break;
                        }
                        return false;

                    }
                });
                menu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return requestModelList.size();
    }

    class ActAptView extends RecyclerView.ViewHolder {

        ImageView arrow;
        LinearLayout hiddenView;
        RelativeLayout ClickBait;
        CardView cardView;
        TextView information;
        RelativeLayout notify;
        TextView name;
        TextView options;

        public ActAptView(@NonNull View itemView) {
            super(itemView);
            arrow = itemView.findViewById(R.id.imageView19);
            hiddenView = itemView.findViewById(R.id.ghost_layout);
            ClickBait = itemView.findViewById(R.id.click_me);
            cardView =itemView.findViewById(R.id.card_for_actapt);
            information = itemView.findViewById(R.id.textView211);
            notify = itemView.findViewById(R.id.touch_notification);
            name = itemView.findViewById(R.id.textView405);
            options = itemView.findViewById(R.id.textViewOptions);

            ClickBait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (hiddenView.getVisibility() == View.VISIBLE) {


                        TransitionManager.beginDelayedTransition(cardView,
                                new AutoTransition());
                        hiddenView.setVisibility(View.GONE);
                        arrow.setImageResource(R.drawable.expand_more);
                        information.setText(R.string.show_more);

                    }  else {
                        TransitionManager.beginDelayedTransition(cardView,
                                new AutoTransition());
                        hiddenView.setVisibility(View.VISIBLE);
                        arrow.setImageResource(R.drawable.expand_less);
                        information.setText(R.string.show_less);
                    }


                }

            });



            notify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendNotification();
                }
            });
        }
    }

    private void sendNotification() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8) {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                    String send_email;

                    //This is a Simple Logic to Send Notification different Device Programmatically....
                    if (LoginFragment.User.equals("ethicalsoulja@gmail.com")) {
                        send_email = "ethicalsoulja@gmail.com";
                   } else {
                        send_email = "somemail@gmail.com"; }

                    try {
                        String jsonResponse;

                        URL url = new URL("https://onesignal.com/api/v1/notifications");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setUseCaches(false);
                        con.setDoOutput(true);
                        con.setDoInput(true);

                        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        con.setRequestProperty("Authorization", "Basic M2RiYTk3MzYtZjkzMy00ZTIwLTgzOWItZjZkZjI4MDc2Y2Zh");
                        con.setRequestMethod("POST");

                        String strJsonBody = "{"
                                + "\"app_id\": \"c8937fe5-9250-4c14-8fe4-870629ec49ff\","

                                + "\"filters\": [{\"field\": \"tag\", \"key\": \" \", \"user_ID\": \"=\", \"value\": \"" + send_email + "\"}],"

                                + "\"data\": {\"foo\": \"bar\"},"
                                + "\"contents\": {\"en\": \"English Message\"}"
                                + "}";


                        System.out.println("strJsonBody:\n" + strJsonBody);

                        byte[] sendBytes = strJsonBody.getBytes("UTF-8");
                        con.setFixedLengthStreamingMode(sendBytes.length);

                        OutputStream outputStream = con.getOutputStream();
                        outputStream.write(sendBytes);

                        int httpResponse = con.getResponseCode();
                        System.out.println("httpResponse: " + httpResponse);

                        if (httpResponse >= HttpURLConnection.HTTP_OK
                                && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                            Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        } else {
                            Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                            jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                            scanner.close();
                        }
                        System.out.println("jsonResponse:\n" + jsonResponse);

                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            }
        });
    }
    }

