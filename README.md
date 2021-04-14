## README

### 一 添加加密算法

若想添加图像置乱算法，请将写好的java文件放在目录<code>/src/Model</code>下，文件名为加密算法的名字，例如：<code>Arnold.java</code>.

同时应注意，加密算法应当引用<code>/src/Model</code>下Scramble的接口，并封装在包src.Model下。然后需要实现Scamble接口里的函数<code>public BufferedImage scrambling(File *srcImageFile*, int *period*);</code>。其中，scrImageFile为输入的原始图像，period为迭代次数，如果不需要此项默认为0，返回值为加密后图像的BufferedImage数据。

```java
package src.Model;

import java.io.*;
import java.awt.image.*;

class *** implements Scramble{
	
    @Override
    public BufferedImage scrambling(File srcImageFile, int period){
    	/*
			Your code
		*/

    }
}
```

