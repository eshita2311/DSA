class Solution {
    public double separateSquares(int[][] squares) {
       
        double high=0;
        double area=0;
        for(int a[]:squares)
        {
            int x=a[0];
            int y=a[1];
            int l=a[2];
            high=Math.max(y+l,high);
            area+=(double)l*l;
        }
        area=area/2;
        double low=0;
        double esp=1e-6;
        double ans=0;
        while(high-low>esp)
        {
            double mid=low+(high-low)/2.0;
            boolean val=isPossible(area,squares,mid);
            
            if(val)
            {
                high=mid;
               
            }
            else
            {
                low=mid;
            }
            
        }
        return low;
    }
    public boolean isPossible(double area,int squares[][],double ll)
    {
        double sum=0;
        for(int a[]:squares)
        {
            // int x=a[0];
            int y=a[1];
            int l=a[2];
            int y2=y+l;

            if(y2<=ll)
            {
                sum+=(double)l*l;
            }
            else if(y<ll)
            {
                double nl=ll-y;
                sum+=(nl*l);
            }
           
        }
      
        return sum>=area;
    }
}