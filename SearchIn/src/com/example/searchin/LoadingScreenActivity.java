package com.example.searchin;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
  
public class LoadingScreenActivity extends Activity  
{   
	private ListOfFormats formats = new ListOfFormats(LoadingScreenActivity.this);

	public static AVLTree tree = new AVLTree();

	public static AVLTree applicationTree = new AVLTree();
	public static AVLTree audioTree = new AVLTree();
	public static AVLTree compressedTree = new AVLTree();
	public static AVLTree contactTree = new AVLTree();
	public static AVLTree imageTree = new AVLTree();
	public static AVLTree otherTree = new AVLTree();
	public static AVLTree videoTree = new AVLTree();
	public static AVLTree documentsTree = new AVLTree();
  
    /** Called when the activity is first created. */  
    @Override  
    public void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new LoadViewTask().execute();         
  
    }  
  
    //To use the AsyncTask, it must be subclassed  
    private class LoadViewTask extends AsyncTask<Void, Void, Void>  
    {  
        //Before running code in separate thread  
        @Override  
        protected void onPreExecute()  
        {  
  
        }  
  
        //The code to be executed in a background thread.  
        @Override  
        protected Void doInBackground(Void... params)  
        {  
            try {
            	formats.listFiles(Environment.getExternalStorageDirectory()
        				.getAbsolutePath() + "/");

        		formats.getContacts();

        		tree.insert(formats.getApplicationList());
        		tree.insert(formats.getAudioList());
        		tree.insert(formats.getCompressedList());
        		tree.insert(formats.getContactList());
        		tree.insert(formats.getImageList());
        		tree.insert(formats.getOtherList());
        		tree.insert(formats.getVideoList());
        		tree.insert(formats.getDocumentsList());

        		applicationTree.insert(formats.getApplicationList());
        		audioTree.insert(formats.getAudioList());
        		compressedTree.insert(formats.getCompressedList());
        		contactTree.insert(formats.getContactList());
        		imageTree.insert(formats.getImageList());
        		otherTree.insert(formats.getOtherList());
        		videoTree.insert(formats.getVideoList());
        		documentsTree.insert(formats.getDocumentsList());
            } catch (Exception e) {
				e.printStackTrace();
			}
            return null;  
        }    
  
        //after executing the code in the thread  
        @Override  
        protected void onPostExecute(Void result)  
        {  
        	LoadingScreenActivity.this.finish();
        	Toast.makeText(LoadingScreenActivity.this, "Loading files successful... :)", Toast.LENGTH_SHORT).show();
        	Intent intent = new Intent(LoadingScreenActivity.this, MainActivity.class);
        	startActivity(intent);
        }  
    }  
}  