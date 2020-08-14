# Gallery256

Gallery256 is a simple way to store and view encrypted images in android.

# Disclaimer

This project is a work in progress. Many features are not yet implemented. This app has never been audited and is not designed to protect against sophisticated adversaries. This is intended for use by those whose threat model is nosey roomates or techy-savy phone-thiefs.

# Overview

Images are copied over from the file-system, where they are then encrypted with a user-chosen symmetric key using android's 256 AES implementation. The encrypted images are then stored in the app's internal storage, and thumbnail data is encrypted before being stored in the SQLIte database.


# Planned

 - Allow for the app to connect to a remote Node.js server
 
    - Store encrypted photos in a MongoDB
  
    - Support exporting photos to a plaintext state on a users' computer



 - General UI improvements


 - Bug fixes
