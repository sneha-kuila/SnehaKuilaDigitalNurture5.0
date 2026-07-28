package com.example.testing.mockito;

import com.example.testing.fileio.FileService;
import com.example.testing.fileio.SimpleFileReader;
import com.example.testing.fileio.SimpleFileWriter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/** Advanced Mockito - Exercise 3: Mocking File I/O */
public class FileServiceTest {

    @Test
    public void testServiceWithMockFileIO() {
        SimpleFileReader mockFileReader = mock(SimpleFileReader.class);
        SimpleFileWriter mockFileWriter = mock(SimpleFileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");

        FileService fileService = new FileService(mockFileReader, mockFileWriter);
        String result = fileService.processFile();

        assertEquals("Processed Mock File Content", result);
        verify(mockFileWriter).write("Processed Mock File Content");
    }
}
