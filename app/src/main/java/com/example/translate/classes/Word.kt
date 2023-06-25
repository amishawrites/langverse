package com.example.translate.classes

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains resource IDs for the default translation, French translation, audio file, and
 * optional image file for that word.
 */
class Word() {

    private var mDefaultTranslation: String = ""
    private var mFrenchTranslation: String = ""
    private var mImageResourceId: Int = NO_IMAGE_RESOURCE
    private var mAudioResource: Int = 0

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the string resource ID for the word in a language that the
     *                             user is already familiar with (such as English)
     * @param frenchTranslation is the string resource Id for the word in the French language
     * @param imageResourceId is the drawable resource ID for the image associated with the word
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    constructor(defaultTranslation: String, frenchTranslation: String, imageResourceID: Int, audioResourceID: Int) : this() {
        mDefaultTranslation = defaultTranslation
        mFrenchTranslation = frenchTranslation
        mImageResourceId = imageResourceID
        mAudioResource = audioResourceID
    }

    /**
     * Create a new Word object.
     *
     * @param defaultTranslation is the string resource ID for the word in a language that the
     *                             user is already familiar with (such as English)
     * @param frenchTranslation is the string resource Id for the word in the French language
     * @param audioResourceId is the resource ID for the audio file associated with this word
     */
    constructor(defaultTranslation: String, frenchTranslation: String, audioResourceID: Int): this(){
        mDefaultTranslation = defaultTranslation
        mFrenchTranslation = frenchTranslation
        mAudioResource = audioResourceID
    }
    fun getDefaultTranslation(): String{
        return mDefaultTranslation
    }

    fun getFrenchTranslation(): String{
        return mFrenchTranslation
    }

    fun getImageResourceId(): Int{
        return mImageResourceId
    }

    fun getAudioResource(): Int{
        return mAudioResource
    }

    /**
     * Returns whether or not there is an image for this word.
     */
    fun hasImage(): Boolean{
        return mImageResourceId != NO_IMAGE_RESOURCE
    }
    companion object {
        const val NO_IMAGE_RESOURCE: Int = -1
    }

}