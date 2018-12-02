<h1>Vk Statistics</h1>
<p>Application that provides you the possibility to view group statistics for the past year from <a href="https://vk.com">Vk.com</a> social network.</p>
<p>Using this app you will able to see:</p>
<ul>
   <li>Group info (name, date creation, users count)</li>
   <li>
      Group statistic:
      <ul>
         <li>
            From which city the users have been join the group.
         </li>
         <li>
            Statistic on the users age.
         </li>
         <li>
            Statistics on the users gender.
         </li>
      </ul>
   </li>
</ul>
<p>To be able to use this app you have to be registered on <a href="https://vk.com">Vk.com</a> social network.</p>
<h1>Stack and architecture.</h1>
<p>
   I have deciced to use clean MVP architecture for this app. <a href="https://github.com/ImangazalievM/CleanArchitectureManifest">This guide</a> is helped me to make it in the right way.
</p>
<p>Here is the list of frameworks/libraries that have been used during the development:</p>
<ul>
   <li>Vk API</li>
   <li>Dagger 2: 2.11</li>
   <li>Rx Java 2: 2.0.6</li>
   <li>Rx Android: 2.0.1</li>
   <li>Meterial search view: 1.4.0</li>
   <li>Circle image view: 2.2.0</li>
   <li>Google Gson: 2.8.0</li>
   <li>Glide: 3.7.0</li>
   <li>Retrofit: 2.3.0</li>
   <li>Moxy: 1.5.2</li>
   <li>MPAndroidChart: 1.5.2</li>
</ul>
<h1>Scenarios</h1>
<p>Here is the possible use cases that you can face using the app.</p>
<p1>Valid scenario.</p1>
<p>User has provided acceess to account after warning and now he/she is able to find the group and view the statistics.</p>

![full-case](https://github.com/poyarkov95/VkStatistics/blob/master/full_case.gif)

<p>No network connection scenario</p>
<p>User having issues with network connection.</p>

![no-network-case](https://github.com/poyarkov95/VkStatistics/blob/master/no-network-case.gif)

<p>No permissions to view the statistic</p>
<p>User don't have permissions to watch the statistic. It's social network's restriction.</p>

![no_permissions_case](https://github.com/poyarkov95/VkStatistics/blob/master/no_permissions_case.gif)
