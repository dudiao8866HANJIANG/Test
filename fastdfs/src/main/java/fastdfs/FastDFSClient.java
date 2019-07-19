package fastdfs;

import java.io.File;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FastDFSClient
{
    private Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
    private TrackerClient trackerClient = null;
    private TrackerServer trackerServer = null;
    private StorageServer storageServer = null;
    private StorageClient1 storageClient1 = null;

    public FastDFSClient(String conf)
            throws Exception
    {
        String path = new File(FastDFSClient.class.getResource("/").getFile()).getCanonicalPath() + File.separator + "lib" + File.separator + "fdfs_client.properties";

        ClientGlobal.init(path);

        this.trackerClient = new TrackerClient();
        this.trackerServer = this.trackerClient.getConnection();
        this.storageServer = this.trackerClient.getStoreStorage(this.trackerServer);
        this.storageClient1 = new StorageClient1(this.trackerServer, this.storageServer);
    }

    public String uploadFile(byte[] file_buff, String file_ext_name)
            throws Exception
    {
        String result = this.storageClient1.upload_file1(file_buff, file_ext_name, null);

        return result;
    }

    public String uploadFile(String local_filename, String file_ext_name)
            throws Exception
    {
        String result = this.storageClient1.upload_file1(local_filename, file_ext_name, null);

        return result;
    }
}
