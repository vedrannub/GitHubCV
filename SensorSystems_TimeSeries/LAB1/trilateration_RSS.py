from __future__ import division
import math
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

#Parameters
max_size = 100

anchors_max = max_size
anchors_min = 0

d0 = 1 #Reference Distance in Meters
distance_max = max_size * math.sqrt(2)
distance_min = 0

PL0 = -36.029 #Reference Path Loss in dB
PL_exponent = 2.386 #Path loss Exponent
PL_noise_std_deviation = 0.6  #Noise Standard Deviation in dB
PL_max = PL0 + 10*PL_exponent*math.log10(distance_max/d0) + 5*PL_noise_std_deviation
PL_min = PL0 + 10*PL_exponent*math.log10(0.01) - 5*PL_noise_std_deviation



true_loc_max = max_size
true_loc_min = 0


#range of rows in csv file
row_start = 0
row_end = 50

#load data
data_set_pd = pd.read_csv('data_set.csv')
#data_set_pd = pd.read_csv('M_test_set_rand_a_noise.csv')
data_set = data_set_pd.values
anchors_p_normalized = data_set[row_start:row_end,0:8]          #anchors centers Normalized
PL_normalized = data_set[row_start:row_end,8:12]                #path losss (RSSI) Normalized
true_loc_normalized = data_set[row_start:row_end,-2:]           #True location Normalized

##UnNormalize
anchors_p = np.zeros ((row_end-row_start,8))
for i in range(0, row_end-row_start):
	for j in range(0, 8):		
		anchors_p [i][j] =  anchors_p_normalized[i][j]*(anchors_max - anchors_min)+anchors_min

true_loc = np.zeros ((row_end-row_start,2))
for i in range(0, row_end-row_start):
	for j in range(0, 2):		
		true_loc [i][j] =  true_loc_normalized[i][j]*(true_loc_max - true_loc_min)+true_loc_min

PL = np.zeros ((row_end-row_start,4)) #RSSI
for i in range(0, row_end-row_start):
	for j in range(0, 4):		
		PL [i][j] =  PL_normalized[i][j]*(PL_max - PL_min)+ PL_min

# PL_normalized [i][j] = (PL [i][j] - PL_min) / (PL_max - PL_min)

###Get distances
anchors_d = np.zeros ((row_end-row_start,4))
for i in range(0, row_end-row_start):
        for j in range(0, 4):
            anchors_d[i][j] = d0 * pow(10, (PL[i,j] - PL0)/(10*PL_exponent))
#                 PL [i][j] = PL0 + 10*PL_exponent*mth.log10(distances[i][j]/d0) + PL_noise_en*PL_noise [i][j]



# A class defines a point as an object with x and y values
class point(object):
    def __init__(self, x, y):
        self.x = x
        self.y = y

# A class defines a anchor as an object with center=location
# and distance from measured node
class anchor(object):
    def __init__(self, point, dist):
        self.center = point
        self.dist = dist

# # A function measures the distance between two points
def get_two_points_distance(p1, p2):
     return math.sqrt(pow((p1.x - p2.x), 2) + pow((p1.y - p2.y), 2))


if __name__ == '__main__' :

    location_arr = np.zeros ((row_end-row_start,2))
    mseError = []


    for n in range(0, row_end-row_start):    
        p1 = point(anchors_p[n,0],anchors_p[n,1])                   #Anchor location
        d1 = anchors_d[n][0]                                        #Distance from Anchor1
        a1 = anchor(p1,d1)                                          #Anchor1

        p2 = point(anchors_p[n,2],anchors_p[n,3])                   #Anchor location
        d2 = anchors_d[n][1]                                        #Distance from Anchor2
        a2 = anchor(p2,d2)                                          #Anchor2

        p3 = point(anchors_p[n,4],anchors_p[n,5])                   #Anchor location
        d3 = anchors_d[n][2]                                        #Distance from Anchor3
        a3 = anchor(p3, d3)                                         #Anchor3 
        
        p4 = point(anchors_p[n,6],anchors_p[n,7])                   #Anchor location
        d4 = anchors_d[n][3]                                        #Distance from Anchor3
        a4 = anchor(p4, d4)                                         #Anchor3 

        location = point(0,0)

        #Trilateration
        a = np.zeros ((3,2))
        b = np.zeros ((3,1))		

        a[0][0] = (2*(p1.x-p3.x))
        a[1][0] = (2*(p2.x-p3.x))
        a[2][0] = (2*(p4.x-p4.x))
        a[0][1] = (2*(p1.y-p3.y))
        a[1][1] = (2*(p2.y-p3.y))
        a[2][1] = (2*(p4.y-p4.y))

        b[0][0] = ((d3**2) -(d1**2) + (p1.x**2) - (p3.x**2) + (p1.y**2) - (p3.y**2))
        b[1][0] = ((d3**2) - (d2**2) + (p2.x**2) - (p3.x**2) + (p2.y**2) - (p3.y**2))
        b[2][0] = ((d3**2) - (d4**2) + (p4.x**2) - (p3.x**2) + (p4.y**2) - (p3.y**2))


        c = np.linalg.lstsq (a, b) [0]

        location.x = c[0]
        location.y = c[1]

        print("\n##############################\n")
        print("For Measurement",n, ":\n")
        print("Anchor1 x,y,d1 ===> ", p1.x, p1.y, d1)
        print("Anchor2 x,y,d2 ===> ", p2.x, p2.y, d2)
        print("Anchor3 x,y,d3 ===> ", p3.x, p3.y, d3)
        print("Anchor4 x,y,d4 ===> ", p4.x, p4.y, d4)



        print("True Location (x,y) --> ",true_loc[n][0], true_loc[n][1])

        print("\nCalculated Location (x,y) --> ",location.x, location.y)
        location_arr[n][0] = location.x
        location_arr[n][1] = location.y
        

        ####### MSE
        A = np.zeros ((2,1))
        B = np.zeros ((2,1))

        A[0] = location.x
        A[1] = location.y
        B[0] = true_loc[n][0]
        B[1] = true_loc[n][1]

        mse = 0        
        mse = np.mean((A - B)**2)

        mseError.append(round(mse))
        print ("\nmse =", round(mse))
    #     print("\n##############################\n")
    print(mseError)


    # ########## Ploting

    plt.ylim(0, 120)
    plt.xlim(0, 120)
    plt.plot (p1.x, p1.y, 'r o')
    plt.plot (p2.x, p2.y, 'r o')
    plt.plot (p3.x, p3.y, 'r o')       
    plt.plot (p4.x, p4.y, 'r o')
    for n in range(0, row_end-row_start):      
        plt.plot (location_arr[n][0], location_arr[n][1], 'g x')
        plt.plot (true_loc[n][0], true_loc[n][1], 'b o')
    plt.show()



    header = 'true_x, true_y, rssi_x, rssi_y, toa_x, toa_y, aoa_x, aoa_y, avg_x, avg_y'

    concatenated_matrix = np.concatenate((true_loc,location_arr), axis=1)
    
    with open('resultsRSS.csv', 'wb') as file:
         np.savetxt(file, concatenated_matrix, delimiter=',', header=header)