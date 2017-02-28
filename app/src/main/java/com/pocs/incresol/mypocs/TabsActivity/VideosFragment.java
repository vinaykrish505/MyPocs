package com.pocs.incresol.mypocs.TabsActivity;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.VideoView;

import com.pocs.incresol.mypocs.R;


public class VideosFragment extends Fragment {

    private Cursor cursor;
    private int columnIndex;
    GridView sdcardimage;
    String[] list;
    VideoAdapter adapter;


    public VideosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(getClass().getSimpleName()+"## oncreate()");
        //Searching Images ID's from Gallery. _ID is the Default id code for all. You can retrive image,contacts,music id in the same way.
        list= new String[]{MediaStore.Video.Media._ID};


        //Retriving Images from Database(SD CARD) by Cursor.
        cursor = getActivity().getApplicationContext().getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, list, null, null, MediaStore.Images.Media._ID);
        columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID);
        adapter= new VideoAdapter(getActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println(getClass().getSimpleName()+"## oncreateview()");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos,
                container, false);
        sdcardimage = (GridView) view.findViewById(R.id.gridview);
        sdcardimage.setAdapter(adapter);
        return view;
    }

    // Adapter for Grid View
    private class VideoAdapter extends BaseAdapter {

        private Context context;

        public VideoAdapter(Context localContext) {

            context = localContext;

        }

        public int getCount() {

            return cursor.getCount();

        }

        public Object getItem(int position) {

            return position;

        }

        public long getItemId(int position) {

            return position;

        }

        public View getView(int position, View convertView, ViewGroup parent) {
            VideoAdapter.ViewHolder holder = new VideoAdapter.ViewHolder();


            if (convertView == null) {
                holder.videossView = new VideoView(context);
                //Converting the Row Layout to be used in Grid View
                convertView = getActivity().getLayoutInflater().inflate(R.layout.row_videos, parent, false);

                //You can convert Layout in this Way with the Help of View Stub. View Stub is newer. Read about ViewStub.Inflate
                // and its parameter.
                //convertView= ViewStub.inflate(context,R.layout.row_images,null);

                convertView.setTag(holder);

            } else {
                holder = (VideoAdapter.ViewHolder) convertView.getTag();
            }
            cursor.moveToPosition(position);
            int imageID = cursor.getInt(columnIndex);

            //In Uri "" + imageID is to convert int into String as it only take String Parameter and imageID is in Integer format.
            //You can use String.valueOf(imageID) instead.
            Uri uri = Uri.withAppendedPath(
                    MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, "" + imageID);

            //Setting Image to View Holder Image View.
            holder.videossView = (VideoView) convertView.findViewById(R.id.videoview);
            holder.videossView.setVideoURI(uri);
//            holder.videossView.setScaleType(VideoView.ScaleType.CENTER_CROP);


            return convertView;

        }

        // View Holder pattern used for Smooth Scrolling. As View Holder pattern recycle the findViewById() object.
        class ViewHolder {
            private VideoView videossView;
        }
    }

}
