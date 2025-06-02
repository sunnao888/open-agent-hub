package com.sunnao.ai.modules.ai.tools;

import cn.hutool.core.io.FileUtil;
import com.sunnao.ai.modules.ai.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

public class FileOperationTool {

    private final String FILE_DIR = FileConstant.FILE_SAVE_DIR + "/file";

    @Tool(description = "读取文件内容")
    public String readFile(@ToolParam(description = "文件名") String fileName) {
        String filePath = FILE_DIR + "/" + fileName;
        try {
            return FileUtil.readUtf8String(filePath);
        } catch (Exception e) {
            return "读取文件出错: " + e.getMessage();
        }
    }

    @Tool(description = "把内容写入到文件")
    public String writeFile(
            @ToolParam(description = "文件名") String fileName,
            @ToolParam(description = "要写入的内容") String content) {
        String filePath = FILE_DIR + "/" + fileName;
        try {
            // 创建目录
            FileUtil.mkdir(FILE_DIR);
            FileUtil.writeUtf8String(content, filePath);
            return "写入成功，文件路径: " + filePath;
        } catch (Exception e) {
            return "写入文件失败: " + e.getMessage();
        }
    }
}
