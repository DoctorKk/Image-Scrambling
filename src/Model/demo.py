import cv2
import numpy as np
import matplotlib.pyplot as plt
import sys

def RGB_correlation(channel,N):
  
  h,w=channel.shape
  
  row=np.random.randint(0,h-1,N)
  col=np.random.randint(0,w-1,N)
  
  x=[]
  h_y=[]
  v_y=[]
  d_y=[]
  for i in range(N):
   
    x.append(channel[row[i]][col[i]])
    
    h_y.append(channel[row[i]][col[i]+1])
    
    v_y.append(channel[row[i]+1][col[i]])
    
    d_y.append(channel[row[i]+1][col[i]+1])
  
  x=x*3
  y=h_y+v_y+d_y

  
  ex=0
  for i in range(N):
    ex+=channel[row[i]][col[i]]
  ex=ex/N
  
  dx=0
  for i in range(N):
    dx+=(channel[row[i]][col[i]]-ex)**2
  dx/=N

  h_ey=0
  for i in range(N):
    h_ey+=channel[row[i]][col[i]+1]
  h_ey/=N
  
  h_dy=0
  for i in range(N):
    h_dy+=(channel[row[i]][col[i]+1]-h_ey)**2
  h_dy/=N
  
  h_cov=0
  for i in range(N):
    h_cov+=(channel[row[i]][col[i]]-ex)*(channel[row[i]][col[i]+1]-h_ey)
  h_cov/=N
  h_Rxy=h_cov/(np.sqrt(dx)*np.sqrt(h_dy))

  
  
  v_ey=0
  for i in range(N):
    v_ey+=channel[row[i]+1][col[i]]
  v_ey/=N
  
  v_dy=0
  for i in range(N):
    v_dy+=(channel[row[i]+1][col[i]]-v_ey)**2
  v_dy/=N
  
  v_cov=0
  for i in range(N):
    v_cov+=(channel[row[i]][col[i]]-ex)*(channel[row[i]+1][col[i]]-v_ey)
  v_cov/=N
  v_Rxy=v_cov/(np.sqrt(dx)*np.sqrt(v_dy))

  
  d_ey=0
  for i in range(N):
    d_ey+=channel[row[i]+1][col[i]+1]
  d_ey/=N
 
  d_dy=0
  for i in range(N):
    d_dy+=(channel[row[i]+1][col[i]+1]-d_ey)**2
  d_dy/=N
  
  d_cov=0
  for i in range(N):
    d_cov+=(channel[row[i]][col[i]]-ex)*(channel[row[i]+1][col[i]+1]-d_ey)
  d_cov/=N
  d_Rxy=d_cov/(np.sqrt(dx)*np.sqrt(d_dy))

  return h_Rxy,v_Rxy,d_Rxy,x,y


def correlation(img,N=3000):
  img=cv2.imread(img)
  h,w,_=img.shape
  B,G,R=cv2.split(img)
  R_Rxy=RGB_correlation(R,N)
  G_Rxy=RGB_correlation(G,N)
  B_Rxy=RGB_correlation(B,N)

  
  plt.rcParams['font.sans-serif'] = ['SimHei']  
  plt.subplot(221)
  plt.imshow(img[:,:,(2,1,0)])
  plt.title('origin')
  
  plt.subplot(222)
  plt.scatter(R_Rxy[3],R_Rxy[4],s=1,c='red')
  plt.title('channelR')

  
  plt.subplot(223)
  plt.scatter(G_Rxy[3],G_Rxy[4],s=1,c='green')
  plt.title('channelG')
  
  plt.subplot(224)
  plt.scatter(B_Rxy[3],B_Rxy[4],s=1,c='blue')
  plt.title('channelB')
  plt.show()

  return R_Rxy[0:3],G_Rxy[0:3],B_Rxy[0:3]


def main():
  print(sys.argv[1])
  img=sys.argv[1]
  
  R_Rxy,G_Rxy,B_Rxy=correlation(img)
  
  print("******relative*****")
  print('channel\tHorizontal\tVertical\tDiagonal')
  print(' R    \t{:.4f}    {:.4f}    {:.4f}'.format(R_Rxy[0],R_Rxy[1],R_Rxy[2]))
  print(' G    \t{:.4f}    {:.4f}    {:.4f}'.format(G_Rxy[0],G_Rxy[1],G_Rxy[2]))
  print(' B    \t{:.4f}    {:.4f}    {:.4f}'.format(B_Rxy[0],B_Rxy[1],B_Rxy[2]))

if __name__== '__main__':
  main()

