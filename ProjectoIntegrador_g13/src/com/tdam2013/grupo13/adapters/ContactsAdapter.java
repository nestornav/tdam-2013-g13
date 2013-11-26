package com.tdam2013.grupo13.adapters;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Contacts.Photo;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.tdam2013.grupo13.R;
import com.tdam2013.grupo13.model.Contact;

public class ContactsAdapter extends CursorAdapter {
	
    /*
     * As a shortcut, defines constants for the
     * column indexes in the Cursor. The index is
     * 0-based and always matches the column order
     * in the projection.
     */
    // Column index of the _ID column
    private int mIdIndex = 0;
    // Column index of the LOOKUP_KEY column
    private int mLookupKeyIndex = 1;
    // Column index of the display name column
    private int mDisplayNameIndex = 2;
    /* Column index of the photo data column. */
    private int mPhotoDataIndex =  3;
    
    private int mHasPhoneNumber = 4;
	

	private LayoutInflater mInflater;
	private Context context;

	// private List<Contact> contacts;
	// private Activity activity;

	public ContactsAdapter(Context context) {
		super(context, null, 0);
		this.context = context;
		// this.activity = activity;
		// this.contacts = contacts;
		mInflater = LayoutInflater.from(context);
	}

	/**
	 * Defines a class that hold resource IDs of each item layout row to prevent
	 * having to look them up each time data is bound to a row.
	 */
	private class ViewHolder {
		TextView displayname;
		QuickContactBadge quickcontact;
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		/*
		 * Inflates the item layout. Stores resource IDs in a in a ViewHolder
		 * class to prevent having to look them up each time bindView() is
		 * called.
		 */
		final View itemView = mInflater.inflate(R.layout.listview_contacts,
				viewGroup, false);
		final ViewHolder holder = new ViewHolder();
		holder.displayname = (TextView) itemView.findViewById(R.id.contact_name);
		holder.quickcontact = (QuickContactBadge) itemView
				.findViewById(R.id.badge_small);
		itemView.setTag(holder);
		return itemView;
	}

	@Override
    public void bindView(
            View view,
            Context context,
            Cursor cursor) {
        final ViewHolder holder = (ViewHolder) view.getTag();
        final String photoData =
                cursor.getString(mPhotoDataIndex);
        final String displayName =
                cursor.getString(mDisplayNameIndex);
        
        // Sets the display name in the layout
        holder.displayname.setText(displayName);
        
        /*
         * Generates a contact URI for the QuickContactBadge.
         */
        final Uri contactUri = Contacts.getLookupUri(
                cursor.getLong(mIdIndex),
                cursor.getString(mLookupKeyIndex));
        holder.quickcontact.assignContactUri(contactUri);
        
        /*
         * Decodes the thumbnail file to a Bitmap.
         * The method loadContactPhotoThumbnail() is defined
         * in the section "Set the Contact URI and Thumbnail"
         */
        Bitmap thumbnailBitmap =
                loadContactPhotoThumbnail(photoData);
        /*
         * Sets the image in the QuickContactBadge
         * QuickContactBadge inherits from ImageView
         */
        if(thumbnailBitmap != null){
        	holder.quickcontact.setImageBitmap(thumbnailBitmap);
        }
	}
	
	 /**
     * Load a contact photo thumbnail and return it as a Bitmap,
     * resizing the image to the provided image dimensions as needed.
     * @param photoData photo ID Prior to Honeycomb, the contact's _ID value.
     * For Honeycomb and later, the value of PHOTO_THUMBNAIL_URI.
     * @return A thumbnail Bitmap, sized to the provided width and height.
     * Returns null if the thumbnail is not found.
     */
    private Bitmap loadContactPhotoThumbnail(String photoData) {
    	if(photoData == null){
    		return BitmapFactory.decodeResource(context.getResources(), R.drawable.unknown);
    	}
        // Creates an asset file descriptor for the thumbnail file.
        AssetFileDescriptor afd = null;
        // try-catch block for file not found
        try {
            // Creates a holder for the URI.
            Uri thumbUri;
            // If Android 3.0 or later
            if (Build.VERSION.SDK_INT
                    >=
                Build.VERSION_CODES.HONEYCOMB) {
                // Sets the URI from the incoming PHOTO_THUMBNAIL_URI
                thumbUri = Uri.parse(photoData);
            } else {
            // Prior to Android 3.0, constructs a photo Uri using _ID
                /*
                 * Creates a contact URI from the Contacts content URI
                 * incoming photoData (_ID)
                 */
                final Uri contactUri = Uri.withAppendedPath(
                        Contacts.CONTENT_URI, photoData);
                /*
                 * Creates a photo URI by appending the content URI of
                 * Contacts.Photo.
                 */
                thumbUri =
                        Uri.withAppendedPath(
                                contactUri, Photo.CONTENT_DIRECTORY);
            }
    
        /*
         * Retrieves an AssetFileDescriptor object for the thumbnail
         * URI
         * using ContentResolver.openAssetFileDescriptor
         */
        afd = this.context.getContentResolver().
                openAssetFileDescriptor(thumbUri, "r");
        /*
         * Gets a file descriptor from the asset file descriptor.
         * This object can be used across processes.
         */
        FileDescriptor fileDescriptor = afd.getFileDescriptor();
        // Decode the photo file and return the result as a Bitmap
        // If the file descriptor is valid
        if (fileDescriptor != null) {
            // Decodes the bitmap
            return BitmapFactory.decodeFileDescriptor(
                    fileDescriptor, null, null);
            }
        // If the file isn't found
        } catch (Exception e) {
            /*
             * Handle file not found errors
             */
        	Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        	return BitmapFactory.decodeResource(context.getResources(), R.drawable.unknown);
        // In all cases, close the asset file descriptor
        } finally {
            if (afd != null) {
                try {
                    afd.close();
                } catch (IOException e) {}
            }
        }
        return null;
    }
    
    public Contact getContactAt(int position){
    	Cursor cur = getCursor();
    	Contact cont = null;
    	if(cur.moveToPosition(position)){
    		cont = new Contact();
    		cont.setId(cur.getString(mIdIndex));
    		cont.setName(cur.getString(mDisplayNameIndex));
    	}
    	return cont;
    }

}
