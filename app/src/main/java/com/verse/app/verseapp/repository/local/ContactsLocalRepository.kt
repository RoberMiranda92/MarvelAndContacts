package com.verse.app.verseapp.repository.local

import android.app.Application
import android.content.ContentResolver
import android.provider.ContactsContract
import com.verse.app.verseapp.repository.ContactsRepository
import com.verse.app.verseapp.repository.models.PhoneContact
import io.reactivex.Observable

class ContactsLocalRepository(private val application: Application) : ContactsRepository {


    override fun getContactList(): Observable<List<PhoneContact>> {

        return Observable.create<List<PhoneContact>> { emitter ->
            try {
                emitter.onNext(getContactsFromPhone())
                emitter.onComplete()
            } catch (ex: Exception) {
                emitter.onError(ex)
            }

        }

    }

    @Throws(SecurityException::class)
    private fun getContactsFromPhone(): List<PhoneContact> {
        val phoneContactList: ArrayList<PhoneContact> = ArrayList<PhoneContact>()

        try {
            val resolver: ContentResolver = application.contentResolver
            val cursor = resolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null,
                null
            )


            if (cursor != null) {
                if (cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        var phoneNumValue:String? = null

                        val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                        val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        val phoneNumber = (cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                        )).toInt()

                        val photoThumbnailValue = (cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI)
                        ))

                        if (phoneNumber > 0) {
                            val cursorPhone = resolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null
                            )
                            if (cursorPhone != null) {
                                if (cursorPhone.count > 0) {
                                    while (cursorPhone.moveToNext()) {
                                        phoneNumValue = cursorPhone.getString(
                                            cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                        )
                                    }
                                }
                            }
                            cursorPhone?.close()
                        }

                        phoneContactList.add(PhoneContact(phoneNumValue, name,photoThumbnailValue))

                    }
                } else {
                    //   toast("No contacts available!")
                }
            }
            cursor?.close()
            return phoneContactList
        } catch (ex: SecurityException) {
            throw ex
        }
    }
}