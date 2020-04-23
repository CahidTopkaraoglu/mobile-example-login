package com.example.week5;

import androidx.core.content.FileProvider;
//https://stackoverflow.com/questions/38200282/android-os-fileuriexposedexception-file-storage-emulated-0-test-txt-exposed
//api 24ve yukarısı olan sürümlerde file provider için FileProvider dan extend olan bir clasın
// dosya erişimlerini kontrol etmesi gerekmektedir.
public class GenericFileProvider extends FileProvider {}
