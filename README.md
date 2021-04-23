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

### 二 我们的算法

传统的算法，都是以整个像素作为单位来进行处理，比如我们所探讨的Arnold算法以及Logistic算法，这样本身也在一定程度上限制了密钥空间的大小，而高维混沌加密的算法又往往存在着高复杂度、效率低的特点；因此如何在保证效率的同时提高密钥空间的大小是首要问题。

受到了一篇论文的启发，这里我们的算法大致可以分为4步：

1. 图像通道分离
2. 对于不同通道的图像采取不同的加密方式，比如可以部分采取Arnold，部分进行灰度变化
3. 将通道打乱组合重排
4. 对最后的图像再进行一次加密

这里的主要点在于不是对于整个像素进行操作，而是对于通道进行操作，其次，不同的通道加密算法不同，而且最后图像的通道数值并不对应于原始图像，并且要先对图像进行一次解密才能获得通道打乱后的原始图像。这在一定程度上大大提升了密钥空间。而且针对于波兹曼熵的评价体系，我们的算法的表现要优于传统的Arnold算法以及Logistic加密算法，而且其他的评价标准之下，我们的算法也并不处在下风。
