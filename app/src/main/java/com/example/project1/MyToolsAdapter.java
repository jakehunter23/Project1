package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class MyToolsAdapter extends RecyclerView.Adapter<ToolsViewHolder> {
     String [] tools_name;
     String tool_desc;
     int [] icon_id;
    public MyToolsAdapter(String [] tools_name, String tool_desc, int [] icon_id){
    this.tools_name=tools_name;
    this.tool_desc=tool_desc;
    this.icon_id=icon_id;
    }

    @Override
    public ToolsViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater tool_inflator=LayoutInflater.from(parent.getContext());
        View tool_list_view=tool_inflator.inflate(R.layout.my_tools_list,parent,false);
        return new ToolsViewHolder(tool_list_view);
    }

    @Override
    public void onBindViewHolder( ToolsViewHolder holder, int position) {
        String toolName = tools_name[position];
        holder.NameTool.setText(toolName);
        holder.DescTool.setText(tool_desc);
        holder.Icons.setImageResource(icon_id[position]);

    }

    @Override
    public int getItemCount() {
        return tools_name.length;
    }
}

class ToolsViewHolder extends RecyclerView.ViewHolder {
    TextView NameTool;
    TextView DescTool;
    ImageView Icons;

    public ToolsViewHolder( View itemView) {
        super(itemView);
        NameTool=itemView.findViewById(R.id.tool_name);
        DescTool=itemView.findViewById(R.id.tool_info);
        Icons=itemView.findViewById(R.id.tools_icon);
    }

}
