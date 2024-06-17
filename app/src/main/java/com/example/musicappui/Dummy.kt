package com.example.musicappui

import androidx.annotation.DrawableRes

data class DummyLib(@DrawableRes val Icon:Int,val name:String)

val libraries= listOf<DummyLib>(
    DummyLib(R.drawable.ic_playlist_green,"Playlist"),
    DummyLib(R.drawable.ic_microphone,"Artists"),
    DummyLib(R.drawable.ic_baseline_album_24,"Album"),
    DummyLib(R.drawable.ic_baseline_music_note_24,"Songs"),
    DummyLib(R.drawable.ic_genre,"Genre")




)


