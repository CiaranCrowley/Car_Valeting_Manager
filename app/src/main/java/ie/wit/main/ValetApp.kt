package ie.wit.main

import android.app.Application
import android.net.Uri
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
import ie.wit.models.ValetModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class ValetApp : Application(), AnkoLogger {

    lateinit var database: DatabaseReference
    //var valets = ArrayList<ValetModel>()
    lateinit var googleSignInClient: GoogleSignInClient
    lateinit var storage : StorageReference
    lateinit var userImage: Uri

    // [START declare_auth]
    lateinit var auth: FirebaseAuth
    // [END declare_auth]

    override fun onCreate(){
        super.onCreate()
        info("Valet App Started")
    }
}